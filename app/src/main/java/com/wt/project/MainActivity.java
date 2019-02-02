package com.wt.project;

import com.wt.project.base.BaseEntity;
import com.wt.project.base.MessageEvent;
import com.wt.project.mvp.contract.MainContract;
import com.wt.project.mvp.presenter.MainPresenter;

/**
 * Created by wangtao on 2019/2/2.
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter loadPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        setToolBarTitle("这是标题");

    }

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    protected void initData() {
//        ApiGet();
    }


    @Override
    public void onReceiveMessage(MessageEvent messageEvent) {
        super.onReceiveMessage(messageEvent);
        switch (messageEvent.getType()){

        }
    }

    /**
     * 接口请求
     */
    private void ApiGet() {
        mPresenter.checkUpdateApp("");
    }

    @Override
    public void getUpdateApp(BaseEntity<String> bean) {

    }
}
