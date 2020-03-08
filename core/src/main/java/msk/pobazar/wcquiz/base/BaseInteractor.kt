package msk.pobazar.wcquiz.base

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseInteractor {
    
   protected var lastDisposable: Disposable? = null
   protected val compositeDisposable = CompositeDisposable()
    
    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
    
    fun dispose() {
        compositeDisposable.clear()
    }
}