package com.wt.project.mvp.contract;


import com.wt.project.base.BaseEntity;
import com.wt.project.base.network.base.IView;

/**
 * Created by Wangtao on 2018/10/25
 */
public class MainContract {
    public interface IMainView extends IView {

        void getUpdateApp(BaseEntity<String> bean);
    }


    public interface IMainPresenter {
        void checkUpdateApp(String code);
    }
}
