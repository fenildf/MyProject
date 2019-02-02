package com.wt.project.mvp.presenter;


import com.wt.project.base.BaseEntity;
import com.wt.project.mvp.contract.MainContract;
import com.wt.project.mvp.model.MainModel;
import com.wt.project.base.network.base.BasePresenter;
import com.wt.project.base.network.exception.ApiException;
import com.wt.project.base.network.subscriber.CommonSubscriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Wangtao on 2018/10/24
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView> implements MainContract.IMainPresenter {


    public MainPresenter(MainContract.IMainView view) {
        super(view);
    }

    @Override
    public void checkUpdateApp(String code) {
        new MainModel().checkUpateApp(code)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        view.showLoadingDialog("");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dismissLoadingDialog("");
                    }
                })
                .subscribe(new CommonSubscriber<BaseEntity<String>>() {
                    @Override
                    public void onSuccess(BaseEntity<String> appUpdateInfo) {
                        view.getUpdateApp(appUpdateInfo);

                    }

                    @Override
                    public void onFail(ApiException e) {

                    }
                });
    }
}
