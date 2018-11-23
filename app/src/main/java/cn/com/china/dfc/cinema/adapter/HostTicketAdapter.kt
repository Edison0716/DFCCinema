package cn.com.china.dfc.cinema.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.entity.CoverFlowEntity
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_CINEMA_NOTICE
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_HOT_MOVIE
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity.Companion.TICKET_MOVIE_COMING_SOON
import cn.com.china.dfc.cinema.manager.CoverFlowLayoutManger
import cn.com.china.dfc.cinema.widget.RecyclerCoverFlow
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.junlong0716.base.module.manager.GlideImageLoader
import com.junlong0716.base.module.rvdecoration.SpaceItemDecoration
import com.junlong0716.base.module.widget.RoundedBanner
import com.youth.banner.BannerConfig


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
    private lateinit var bannerImg: ArrayList<Int>
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
                bannerImg = ArrayList()
                bannerImg.add(R.mipmap.host_banner_test)
                bannerImg.add(R.mipmap.host_banner_test)
                bannerImg.add(R.mipmap.host_banner_test)

                val roundedBanner = helper.getView<RoundedBanner>(R.id.host_banner)
                //设置指示器样式
                roundedBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)

                //设置指示器位置
                roundedBanner.setIndicatorGravity(BannerConfig.CENTER)

                //设置图片加载器
                roundedBanner.setImageLoader(GlideImageLoader())

                //设置自动播放
                roundedBanner.isAutoPlay(true)

                //设置延迟时间
                roundedBanner.setDelayTime(5000)

                //设置图片
                roundedBanner.setImages(bannerImg)

                //开始轮播
                roundedBanner.start()
            }

            TicketMultipleItemEntity.TICKET_MOVIE_COMING_SOON -> {
                val data = ArrayList<String>()
                for (i in 0 until 8) {
                    data.add("")
                }

                val rvComingSoon = helper.getView<RecyclerView>(R.id.host_rv_coming_soon)
                val manager = GridLayoutManager(mContext, 4)
                rvComingSoon.layoutManager = manager
                manager.isSmoothScrollbarEnabled = true
                rvComingSoon.setHasFixedSize(true)
                rvComingSoon.isFocusable = false
                rvComingSoon.isFocusableInTouchMode = false
                rvComingSoon.isNestedScrollingEnabled = false
                rvComingSoon.adapter =
                        HostComingSoonAdapter(data, R.layout.host_item_ticket_coming_soon_movie, mContext)
                rvComingSoon.addItemDecoration(SpaceItemDecoration(20))
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