package com.wt.project.base.network.base;

/**
 * Created by GaoSheng on 2016/11/26.
 * 17:20
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.base
 * mvp之v
 */

public interface IView {
    void showLoadingDialog(String msg);

    void dismissLoadingDialog(String msg);
}
