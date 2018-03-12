package com.wt.kotlin.mvp.model

import com.wt.kotlin.RetrofitClient
import com.wt.kotlin.api.TestApi
import com.wt.kotlin.bean.FuckGoods
import com.wt.kotlin.bean.JsonResult
import com.wt.kotlin.mvp.contract.TestContract
import io.reactivex.Observable

/**
 * Created by wangt on 2018/3/8.
 */
class TestModel : TestContract.Model {
    override fun getRandom(type: String): Observable<JsonResult<List<FuckGoods>>> {
        val retrofitClient = RetrofitClient.instance
        val apiService = retrofitClient.create(TestApi::class.java)
        return apiService!!.getRandom(type)
    }
}