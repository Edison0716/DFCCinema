package cn.com.china.dfc.cinema.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/22 5:35 PM
 *@modified by:
 */
class HostComingSoonAdapter(data:ArrayList<String>,resLayout:Int):BaseQuickAdapter<String,BaseViewHolder>(resLayout,data){
    override fun convert(helper: BaseViewHolder?, item: String?) {

    }
}