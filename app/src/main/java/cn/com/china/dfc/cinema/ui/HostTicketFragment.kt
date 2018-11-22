package cn.com.china.dfc.cinema.ui

import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.adapter.HostTicketAdapter
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_HOT_MOVIE
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.junlong0716.base.module.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/20 3:23 PM
 *@modified by:
 */
class HostTicketFragment : BaseFragment<HostTicketPresenter>(), HostTicketContract.View,
    HostTicketAdapter.OnDragListener {
    private lateinit var mTicketList: ArrayList<TicketMultipleItemEntity>
    private var mCoverFlowPage = 0
    lateinit var mHostTicketAdapter: HostTicketAdapter
    private var mHandler: Handler = WithoutLeakHandler(this)
    private var mDisposable: Disposable? = null

    companion object {
        private class WithoutLeakHandler(fragment: HostTicketFragment) : Handler() {
            private var mFragment: WeakReference<HostTicketFragment> = WeakReference(fragment)
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                mFragment.get()!!.mHostTicketAdapter.smoothToPos(msg!!.what)
            }
        }
    }


    override fun attachPresenter() {
        mPresenter = HostTicketPresenter()
        mPresenter!!.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.host_fragment_ticket

    override fun initViews(mRootView: View?) {
        AdaptScreenUtils.adaptHeight(super.getResources(),749)
        AdaptScreenUtils.adaptWidth(super.getResources(),375)

        val titleBarContainer = mRootView!!.findViewById<View>(R.id.host_include_title_bar)
        val tvTitle = titleBarContainer!!.findViewById<TextView>(R.id.host_tv_title)
        val flTitleBarContainer = titleBarContainer.findViewById<FrameLayout>(R.id.host_fl_title_bar_container)
        tvTitle.text = getString(R.string.host_app_name)
        BarUtils.addMarginTopEqualStatusBarHeight(flTitleBarContainer)
        val rvList = mRootView.findViewById<RecyclerView>(R.id.host_rv_list)
        mTicketList = ArrayList()
        mTicketList.add(TicketMultipleItemEntity(TICKET_HOT_MOVIE))
        rvList.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        mHostTicketAdapter = HostTicketAdapter(mTicketList, this)
        rvList.adapter = mHostTicketAdapter
        startCoverFlowRv()
    }

    private fun startCoverFlowRv() {
        Observable.interval(0, 5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                }

                override fun onNext(t: Long) {
                    val message = Message()
                    message.what = mCoverFlowPage
                    mHandler.sendMessage(message)
                    if (mCoverFlowPage >= 4) {
                        mCoverFlowPage = 0
                    } else {
                        mCoverFlowPage++
                    }
                }

                override fun onError(e: Throwable) {

                }
            })

    }


    override fun lazyFetchData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }


    override fun onDragFinishListener() {
        //startCoverFlowRv()
    }

    override fun onDragListener() {
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }

    override fun onSelectedListener(pos: Int) {
        mCoverFlowPage = pos
    }
}