package cn.com.china.dfc.cinema.adapter

import cn.com.china.dfc.cinema.entity.CoverFlowEntity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/21 3:24 PM
 *@modified by:
 */
class HostCoverFlowAdapter(data:ArrayList<CoverFlowEntity>,resLayout:Int):BaseQuickAdapter<CoverFlowEntity,BaseViewHolder>(resLayout,data){
    override fun convert(helper: BaseViewHolder?, item: CoverFlowEntity?) {

    }
}