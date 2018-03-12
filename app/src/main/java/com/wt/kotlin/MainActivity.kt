package com.wt.kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.wt.kotlin.bean.FuckGoods
import com.wt.kotlin.mvp.contract.TestContract
import com.wt.kotlin.mvp.model.TestModel
import com.wt.kotlin.mvp.presenter.TestPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TestContract.View {

    var mPresenter: TestPresenter? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mPresenter?.getRandom("Android")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mPresenter?.getRandom("Android")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mPresenter?.getRandom("Android")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = TestPresenter(TestModel(), this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mPresenter?.getRandom("Android")
    }

    override fun onRandom(goods: FuckGoods) {
        message.setText(goods.desc)
    }


}
