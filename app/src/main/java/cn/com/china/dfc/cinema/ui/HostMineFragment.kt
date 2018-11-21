package cn.com.china.dfc.cinema.ui

import android.view.View
import android.widget.TextView
import cn.com.china.dfc.cinema.R
import com.blankj.utilcode.util.BarUtils
import com.junlong0716.base.module.base.BaseFragment

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/20 3:23 PM
 *@modified by:
 */
class HostMineFragment : BaseFragment<HostMinePresenter>(), HostMineContract.View {
    override fun attachPresenter() {
        mPresenter = HostMinePresenter()
        mPresenter!!.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.host_fragment_mine

    override fun initViews(mRootView: View?) {

    }

    override fun lazyFetchData() {

    }
}