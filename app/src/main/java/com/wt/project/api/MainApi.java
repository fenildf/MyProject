package com.wt.project.api;

import com.wt.project.base.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Wangtao on 2018/10/25
 */
public interface MainApi {

    /**
     * 检查App版本
     */
    @FormUrlEncoded
    @POST("mapi/School/getClientVersion")
    Observable<BaseEntity<String>> checkUpdateApp(@Field("code") String code);
}
