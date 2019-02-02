package com.wt.project.base.network.subscriber;

import android.util.Log;

import com.wt.project.base.BaseEntity;
import com.wt.project.base.network.exception.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by wangtao on 2018/10/6.
 */

public abstract class CommonSubscriber<T> implements Observer<T> {


    public CommonSubscriber() {
    }

    private static final String TAG = "CommonSubscriber";

    @Override
    public void onSubscribe(Disposable d) {
//        if (!AppUtil.isNetConnect(EdusohoApp.app.getApplicationContext())) {
//            CommonUtil.longToast(EdusohoApp.app.getApplicationContext(), "没有网络服务！请检查网络设置。");
//            return;
//        }
    }

    @Override
    public void onNext(T t) {
        if (t instanceof BaseEntity) {
            BaseEntity tResponse = ((BaseEntity) t);
            if (tResponse.getCode() != 0) {
                onFail(new ApiException(tResponse.getCode(), tResponse.getMessage()));
            } else {
                onSuccess(t);
            }
        } else {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e.getMessage() != null) {
            Log.e(TAG, e.getMessage());
        }
        onFail(new ApiException(e.hashCode(), e.getMessage()));
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "请求成功");
    }

    /**
     * 请求正确
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 请求错误
     *
     * @param e
     */
    public abstract void onFail(ApiException e);

}
