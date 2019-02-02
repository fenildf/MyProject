package com.wt.project.base.network;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {
    private static final int DEFAULT_TIME_OUT = 20;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 20;

    private ApiUtil() {
    }

    private static class SingletonHolder {
        private static final ApiUtil INSTANCE = new ApiUtil();
    }

    public static ApiUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private OkHttpClient InterceptClient(final boolean isNewApi) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 创建 OKHttpClient
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作 超时时间

        /**
         * 设置头信息
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .method(originalRequest.method(), originalRequest.body());
                requestBuilder.addHeader("Accept", "application/vnd.edusoho.v2+json");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(headerInterceptor);

        /**
         * 调试信息
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    public <T> T create(Class<T> service) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(this.InterceptClient(true))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.baidu.com")
                .build();
        return mRetrofit.create(service);
    }
}