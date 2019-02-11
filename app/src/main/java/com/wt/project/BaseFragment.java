package com.wt.project;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wt.project.base.MessageEvent;
import com.wt.project.base.network.base.IPresenter;
import com.wt.project.base.network.base.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.reactivex.annotations.Nullable;

/**
 * Created by JesseHuang on 15/4/24.
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {

    protected Activity mActivity;
    protected View mContainerView;
    public String mTitle;
    protected Context mContext;

    protected P mPresenter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mContext = mActivity.getBaseContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainerView = inflaterView(inflater, container, savedInstanceState);

        mPresenter = loadPresenter();
        initView(mContainerView);
        initData();
        return mContainerView;
    }

    protected abstract View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle);

    protected P loadPresenter() {
        return null;
    }

    protected void initView(View view) {
    }

    protected void initData() {
    }

    /**
     * 是否可以使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void showLoadingDialog(String msg) {
    }

    @Override
    public void dismissLoadingDialog(String msg) {
    }

    @Subscribe
    public void onReceiveMessage(MessageEvent messageEvent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        if (mPresenter != null) {
            mPresenter.detach();//在presenter中解绑释放view
            mPresenter = null;
        }
    }

}
