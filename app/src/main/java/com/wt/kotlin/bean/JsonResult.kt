package com.wt.kotlin.bean

/**
 * Created by wangt on 2018/3/9.
 */
class JsonResult<T>(val error :Boolean,
                    val results:T){
}