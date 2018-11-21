package cn.com.china.dfc.cinema.ui

import android.os.Bundle
import android.view.View
import cn.com.china.dfc.cinema.R
import cn.com.china.dfc.cinema.base.BaseActivity
import kotlinx.android.synthetic.main.host_layout_bottom_bar.*


class HostHomeActivity : BaseActivity<HostHomePresenter>(), HostHomeContract.View, View.OnClickListener {
    //init fragment
    private var mHostTicketFragment: HostTicketFragment? = null
    private var mHostGoodsFragment: HostGoodsFragment? = null
    private var mHostMineFragment: HostMineFragment? = null

    override fun attachPresenter() {
        mPresenter = HostHomePresenter()
        mPresenter!!.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.host_activity_home

    override fun initView(savedInstanceState: Bundle?) {
        initFragment(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        host_ll_ticket.setOnClickListener(this)
        host_ll_goods.setOnClickListener(this)
        host_ll_mine.setOnClickListener(this)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mHostTicketFragment = supportFragmentManager.findFragmentByTag("TICKET_FRAGMENT") as HostTicketFragment
            mHostGoodsFragment = supportFragmentManager.findFragmentByTag("GOODS_FRAGMENT") as HostGoodsFragment
            mHostMineFragment = supportFragmentManager.findFragmentByTag("MINE_FRAGMENT") as HostMineFragment
        } else {
            mHostTicketFragment = HostTicketFragment()
            mHostGoodsFragment = HostGoodsFragment()
            mHostMineFragment = HostMineFragment()

            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_container, mHostTicketFragment, "TICKET_FRAGMENT")
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(mHostTicketFragment).commit()
    }

    override fun registerRxBus() {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.host_ll_ticket -> {
                val mineFragment = supportFragmentManager.findFragmentByTag("TICKET_FRAGMENT")
                if (mineFragment == null) {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fl_container, mHostTicketFragment, "TICKET_FRAGMENT")
                        .show(mHostTicketFragment)
                        .hide(mHostMineFragment)
                        .hide(mHostGoodsFragment)
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction().show(mHostTicketFragment)
                        .hide(mHostMineFragment)
                        .hide(mHostGoodsFragment)
                        .commit()
                }
            }

            R.id.host_ll_goods -> {
                val mineFragment = supportFragmentManager.findFragmentByTag("GOODS_FRAGMENT")
                if (mineFragment == null) {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fl_container, mHostGoodsFragment, "GOODS_FRAGMENT")
                        .show(mHostGoodsFragment)
                        .hide(mHostTicketFragment)
                        .hide(mHostMineFragment)
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction().show(mHostGoodsFragment)
                        .hide(mHostTicketFragment)
                        .hide(mHostMineFragment)
                        .commit()
                }
            }

            R.id.host_ll_mine -> {
                val mineFragment = supportFragmentManager.findFragmentByTag("MINE_FRAGMENT")
                if (mineFragment == null) {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fl_container, mHostMineFragment, "MINE_FRAGMENT")
                        .show(mHostMineFragment)
                        .hide(mHostTicketFragment)
                        .hide(mHostGoodsFragment)
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction().show(mHostMineFragment)
                        .hide(mHostTicketFragment)
                        .hide(mHostGoodsFragment)
                        .commit()
                }
            }
        }
    }
}
