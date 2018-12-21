package cn.com.china.dfc.cinema

import android.content.Context
import com.didi.virtualapk.PluginManager
import com.junlong0716.base.module.BaseApplication
import com.orhanobut.logger.Logger
import com.orhanobut.logger.AndroidLogAdapter

/**
 *@author: EdsionLi
 *@description:
 *@date: Created in 2018/11/20 12:52 PM
 *@modified by:
 */

class MyApp : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        PluginManager.getInstance(base).init()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}