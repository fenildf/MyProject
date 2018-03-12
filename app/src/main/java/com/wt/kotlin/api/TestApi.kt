package com.wt.kotlin.api

import com.wt.kotlin.bean.FuckGoods
import com.wt.kotlin.bean.JsonResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by wangt on 2018/3/8.
 */
interface TestApi {


    /**
     * 手气不错
     */

    @GET("random/data/{type}/1")
    fun getRandom(@Path("type") type: String): Observable<JsonResult<List<FuckGoods>>>
}