package cn.com.china.dfc.cinema.ui

import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.adapter.HostTicketAdapter
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_CINEMA_NOTICE
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_HOT_MOVIE
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_MOVIE_COMING_SOON
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
    private var overallXScroll = 0//用来记住滑动的距离
    private val height = 100// 滑动开始变色的高,真实项目中此高度是由广告轮播或其他首页view高度决定
    private lateinit var mTvTitle: TextView
    private lateinit var mTvCinemaAddress: TextView

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
        AdaptScreenUtils.adaptHeight(super.getResources(), 749)
        AdaptScreenUtils.adaptWidth(super.getResources(), 375)

        val titleBarContainer = mRootView!!.findViewById<View>(R.id.host_include_title_bar)
        mTvTitle = titleBarContainer!!.findViewById(R.id.host_tv_title)
        mTvCinemaAddress = titleBarContainer.findViewById(R.id.host_tv_cinema_address)
        val flTitleBarContainer = titleBarContainer.findViewById<FrameLayout>(R.id.host_fl_title_bar_container)
        mTvTitle.text = getString(R.string.host_app_name)
        BarUtils.addMarginTopEqualStatusBarHeight(flTitleBarContainer)
        val rvList = mRootView.findViewById<RecyclerView>(R.id.host_rv_list)
        mTicketList = ArrayList()
        mTicketList.add(TicketMultipleItemEntity(TICKET_HOT_MOVIE))
        mTicketList.add(TicketMultipleItemEntity(TICKET_CINEMA_NOTICE))
        mTicketList.add(TicketMultipleItemEntity(TICKET_MOVIE_COMING_SOON))

        rvList.layoutManager = object : LinearLayoutManager(activity!!.applicationContext) {
            override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                return RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }
        mHostTicketAdapter = HostTicketAdapter(mTicketList, this)
        rvList.adapter = mHostTicketAdapter
        startCoverFlowRv()
        val headerView = LayoutInflater.from(activity).inflate(R.layout.host_item_ticket_header, null)
        mHostTicketAdapter.addHeaderView(headerView)

        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                overallXScroll += dy// 累加y值 解决滑动一半y值为0
                when {
                    overallXScroll <= 0 -> {
                        // mTvTitle.setTextColor(Color.argb(255, 0, 0, 0))
                        mTvCinemaAddress.visibility = View.GONE
                        mTvTitle.visibility = View.VISIBLE
                    }
                    overallXScroll in 1..height -> {
                        //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
//                        val scale = overallXScroll.toFloat() / height
//                        val alpha = - 255 * scale
//                        mTvTitle.setTextColor(Color.argb(alpha.toInt(), 0, 0, 0))
                        mTvCinemaAddress.visibility = View.GONE
                        mTvTitle.visibility = View.VISIBLE
                        //  mTvTitle.setTextColor(Color.argb(255, 0, 0, 0))
                    }
                    overallXScroll > height -> {
                        //     mTvTitle.setTextColor(Color.argb(0, 0, 0, 0))
                        mTvCinemaAddress.visibility = View.VISIBLE
                        mTvTitle.visibility = View.GONE
                    }
                    else -> {

                    }
                }
            }
        })
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