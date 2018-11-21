package cn.com.china.dfc.cinema.entity

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/21 2:40 PM
 *@modified by:
 */
class TicketMultipleItemEntity(itemType:Int) : MultiItemEntity {
    private var mItemType = itemType

    companion object {
        //热播电影
        const val TICKET_HOT_MOVIE = 1
        //影院公告
        const val TICKET_CINEMA_NOTICE = 2
        //即将上映
        const val TICKET_MOVIE_COMING_SOON = 3
    }
    override fun getItemType(): Int {
        return mItemType
    }
}