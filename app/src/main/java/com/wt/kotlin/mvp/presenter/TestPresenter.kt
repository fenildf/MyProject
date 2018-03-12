package com.wt.kotlin.mvp.presenter

import com.wt.kotlin.mvp.contract.TestContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wangt on 2018/3/9.
 */
class TestPresenter(model: TestContract.Model, view: TestContract.View) : TestContract.Presenter {

    var model: TestContract.Model
    var view: TestContract.View

    init {
        this.model = model
        this.view = view
    }


    override fun getRandom(type: String) {
        model.getRandom(type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    if (!res.error) {
                        view.onRandom(res.results[0])
                    }
                }, {})
    }

}