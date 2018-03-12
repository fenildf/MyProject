package com.wt.kotlin.mvp.contract

import com.wt.kotlin.bean.FuckGoods
import com.wt.kotlin.bean.JsonResult
import io.reactivex.Observable

/**
 * Created by wangt on 2018/3/8.
 */
interface TestContract {

    interface View{
        fun onRandom(goods: FuckGoods)

    }
    interface Model{

        fun getRandom(type: String): Observable<JsonResult<List<FuckGoods>>>
    }
    interface Presenter{

        fun getRandom(type: String)
    }
}