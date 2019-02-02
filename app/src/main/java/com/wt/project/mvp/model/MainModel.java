package com.wt.project.mvp.model;


import com.wt.project.api.MainApi;
import com.wt.project.base.BaseEntity;
import com.wt.project.base.network.ApiUtil;
import com.wt.project.base.network.base.IModel;

import io.reactivex.Observable;

/**
 * Created by Wangtao on 2018/10/24
 */
public class MainModel implements IModel {

    private MainApi api;

    private MainApi ApiInstance() {
        if (api != null) {
            return api;
        } else {
            return ApiUtil.getInstance().create(MainApi.class);
        }
    }

    public Observable<BaseEntity<String>> checkUpateApp(String code) {
        api = ApiInstance();
        return api.checkUpdateApp(code);
    }
}
