package com.wt.kotlin

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by wangt on 2018/3/9.
 */
class RetrofitClient private constructor() {

    val BASE_URL: String = "http://gank.io/api/"
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    val DEFAULT_TIMEOUT: Long = 20
//    val url = baseUrl

    init {
        /* //缓存地址
         if (httpCacheDirectory == null) {
             httpCacheDirectory = File(mContext.cacheDir, "app_cache")
         }
         try {
             if (cache == null) {
                 cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
             }
         } catch (e: Exception) {
             Log.e("OKHttp", "Could not create http cache", e)
         }*/
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

    }

    companion object {
        var instance = RetrofitClient()
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }


}