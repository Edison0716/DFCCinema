package cn.com.china.dfc.cinema.adapter

import android.content.Context
import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.entity.CoverFlowEntity
import cn.com.china.dfc.cinema.entity.TicketMultipleItemEntity
import cn.com.china.dfc.cinema.manager.CoverFlowLayoutManger
import cn.com.china.dfc.cinema.widget.RecyclerCoverFlow
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/21 2:38 PM
 *@modified by:
 */
class HostTicketAdapter(data: ArrayList<TicketMultipleItemEntity>) :
    BaseMultiItemQuickAdapter<TicketMultipleItemEntity, BaseViewHolder>(data) {
    private lateinit var mCoverFlowData: ArrayList<CoverFlowEntity>
    private lateinit var rvCoverList: RecyclerCoverFlow

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
        mCoverFlowData.add(CoverFlowEntity(1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(1, 3.5))
        mCoverFlowData.add(CoverFlowEntity(1, 3.5))
        rvCoverList!!.adapter = HostCoverFlowAdapter(mCoverFlowData, R.layout.host_item_ticket_hot_cover_flow)
        rvCoverList.setOnItemSelectedListener(CoverFlowLayoutManger.OnSelected {

        })
    }

    fun smoothToPos(pos: Int) {
        rvCoverList.smoothScrollToPosition(pos)
    }
}