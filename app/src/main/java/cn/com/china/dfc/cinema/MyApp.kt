package cn.com.china.dfc.cinema

import android.content.Context
import com.didi.virtualapk.PluginManager
import com.junlong0716.base.module.BaseApplication

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/20 12:52 PM
 *@modified by:
 */
class MyApp : BaseApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        PluginManager.getInstance(base).init();
    }
}