package cn.com.china.dfc.cinema.base

import android.support.v4.content.ContextCompat
import cn.com.china.dfc.cinema.R
import com.junlong0716.base.module.base.BaseActivity
import com.junlong0716.base.module.base.IPresenter
import com.junlong0716.base.module.util.RomUtil
import com.junlong0716.base.module.util.StatusBarUtil

/**
 *@author: EdsionLi
 *@description: 基类
 *@date: Created in 2018/11/20 2:27 PM
 *@modified by:
 */
abstract class BaseActivity<P : IPresenter>: BaseActivity<P>(){
    override fun beforeSetLayout() {
        if (RomUtil.isLightStatusBarAvailable()) {
            StatusBarUtil.setLightStatusBar(this, true)
            StatusBarUtil.setTranslucentForCoordinatorLayout(this, ContextCompat.getColor(this, R.color.common_colorPrimary))
        }
    }
}