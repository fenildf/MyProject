package com.wt.kotlin.bean

/**
 * Created by wangt on 2018/3/9.
 */
data class FuckGoods(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String

){
    fun hasImg():Boolean {
        return images != null
    }
    fun create() = createdAt.substring(0,10)

}