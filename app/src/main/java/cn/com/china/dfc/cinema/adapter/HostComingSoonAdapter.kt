package cn.com.china.dfc.cinema.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/22 5:35 PM
 *@modified by:
 */
class HostComingSoonAdapter(data: ArrayList<String>, resLayout: Int,context:Context) :
    BaseQuickAdapter<String, BaseViewHolder>(resLayout, data) {
    private var mResLayout = resLayout
    private var mContext1 = context
    override fun convert(helper: BaseViewHolder?, item: String?) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(this@HostComingSoonAdapter.mContext1).inflate(mResLayout, parent, false)
        return BaseViewHolder(view)
    }
}