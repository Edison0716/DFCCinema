package cn.com.china.dfc.cinema.ui

import android.view.View
import cn.com.china.dfc.cinema.R
import com.junlong0716.base.module.base.BaseFragment

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/20 3:23 PM
 *@modified by:
 */
class HostGoodsFragment:BaseFragment<HostGoodsPresenter>(),HostGoodsContract.View{
    override fun attachPresenter() {
        mPresenter = HostGoodsPresenter()
        mPresenter!!.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.host_fragment_goods

    override fun initViews(mRootView: View?) {

    }

    override fun lazyFetchData() {

    }
}