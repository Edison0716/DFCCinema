package cn.com.china.dfc.cinema.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.entity.CoverFlowEntity
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity
import cn.com.china.dfc.cinema.manager.CoverFlowLayoutManger
import cn.com.china.dfc.cinema.widget.RecyclerCoverFlow
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.PagerSnapHelper


/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/21 2:38 PM
 *@modified by:
 */
class HostTicketAdapter(data: ArrayList<TicketMultipleItemEntity>, onDragListener: OnDragListener) :
    BaseMultiItemQuickAdapter<TicketMultipleItemEntity, BaseViewHolder>(data) {
    private lateinit var mCoverFlowData: ArrayList<CoverFlowEntity>
    private lateinit var rvCoverList: RecyclerCoverFlow
    private lateinit var mHostCoverFlowAdapter: HostCoverFlowAdapter
    private var mOnDragListener = onDragListener

    init {
        addItemType(TicketMultipleItemEntity.TICKET_HOT_MOVIE, R.layout.host_item_ticket_multi_hot_movie)
        addItemType(TicketMultipleItemEntity.TICKET_CINEMA_NOTICE, R.layout.host_item_ticket_multi_notice_movie)
        addItemType(
            TicketMultipleItemEntity.TICKET_MOVIE_COMING_SOON,
            R.layout.host_item_ticket_multi_coming_soon_movie
        )
    }

    override fun convert(helper: BaseViewHolder?, item: TicketMultipleItemEntity?) {
        when (helper!!.itemViewType) {
            TicketMultipleItemEntity.TICKET_HOT_MOVIE -> {
                rvCoverList = helper.getView(R.id.host_rv_cover_flow)
                setRvCoverList(rvCoverList, mContext)
            }

            TicketMultipleItemEntity.TICKET_CINEMA_NOTICE -> {

            }

            TicketMultipleItemEntity.TICKET_MOVIE_COMING_SOON -> {

            }
            else -> {
            }
        }
    }

    private fun setRvCoverList(rvCoverList: RecyclerCoverFlow?, mContext: Context?) {
        mCoverFlowData = ArrayList()
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test2, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test3, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test2, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test3, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test2, 3.5))
        mCoverFlowData.add(CoverFlowEntity(R.mipmap.host_test3, 3.5))
        mHostCoverFlowAdapter = HostCoverFlowAdapter(mCoverFlowData, R.layout.host_item_ticket_hot_cover_flow)
        rvCoverList!!.adapter = mHostCoverFlowAdapter
        rvCoverList.setFlingScale(0.01)
        rvCoverList.setOnItemSelectedListener(CoverFlowLayoutManger.OnSelected {
            mOnDragListener.onSelectedListener(it)
        })
        mHostCoverFlowAdapter.setOnItemClickListener { adapter, view, position ->
            //ToastUtils.showShort(position.toString())
            rvCoverList.smoothScrollToPosition(position)
        }
        rvCoverList.setOnDragListener(object : CoverFlowLayoutManger.OnDrag {
            override fun onDragListener() {
                mOnDragListener.onDragListener()
            }

            override fun onDragFinishListener() {
                mOnDragListener.onDragFinishListener()
            }
        })
    }

    fun smoothToPos(pos: Int) {
        rvCoverList.smoothScrollToPosition(pos)
    }

    interface OnDragListener {
        /**
         * 开始拖拽监听
         */
        fun onDragListener()

        /**
         * 拖拽完成监听
         */
        fun onDragFinishListener()


        fun onSelectedListener(pos: Int)
    }
}