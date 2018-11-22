package cn.com.china.dfc.cinema.adapter

import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.entity.CoverFlowEntity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.junlong0716.base.module.glide.GlideUtils

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/21 3:24 PM
 *@modified by:
 */
class HostCoverFlowAdapter(data: ArrayList<CoverFlowEntity>, resLayout: Int) :
    BaseQuickAdapter<CoverFlowEntity, BaseViewHolder>(resLayout, data) {
    override fun convert(helper: BaseViewHolder?, item: CoverFlowEntity?) {
        GlideUtils.showImageFade(mContext, item!!.imgUrl, helper!!.getView(R.id.host_iv_movie_img))
    }
}