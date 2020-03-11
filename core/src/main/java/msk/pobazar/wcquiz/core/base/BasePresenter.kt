package msk.pobazar.wcquiz.core.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {


    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        resetCompositeDisposable()
    }

    fun Disposable.bind() = disposables.add(this)

    fun Disposable.unbind() = disposables.remove(this)

    fun resetCompositeDisposable() {
        disposables.clear()
    }
}