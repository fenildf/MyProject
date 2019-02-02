package com.wt.project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wt.project.base.MessageEvent;
import com.wt.project.base.network.base.IPresenter;
import com.wt.project.base.network.base.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by wangtao on 2019/2/2.
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    protected BaseActivity mActivity;
    protected Context mContext;

    protected P mPresenter;

    private ImageView mToolbarback;
    private TextView mToolbarTitle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mActivity = this;
        mContext = this;

        mToolbar = findViewById(R.id.toolbar);
        mToolbarback = findViewById(R.id.toolbar_back);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mPresenter = loadPresenter();
        initView();

        EventBus.getDefault().register(this);

        initData();
    }

    protected P loadPresenter() {
        return null;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();


    @Subscribe
    public void onReceiveMessage(MessageEvent messageEvent) {

    }

    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar);
    }

    public ImageView getToolbarback() {
        return mToolbarback;
    }

    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    private void showBack() {
        if (mToolbarback != null) {
            if (isShowBacking()) {
                mToolbarback.setVisibility(View.VISIBLE);
                mToolbarback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            } else {
                mToolbarback.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }


    @Override
    public void showLoadingDialog(String msg) {
    }

    @Override
    public void dismissLoadingDialog(String msg) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != getToolbar()) {
            showBack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.detach();//在presenter中解绑释放view
            mPresenter = null;
        }
    }
}
