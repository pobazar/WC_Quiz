package msk.pobazar.wcquiz.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleBaseInteractor<T> : BaseInteractor() {
    internal abstract fun buildInteractor(): Single<T>
    
    fun execute(
            doOnSubscribe: () -> Unit = {},
            onSuccess: ((t: T) -> Unit),
            onError: ((t: Throwable) -> Unit)
    ) {
        disposeLast()
        lastDisposable = buildInteractor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { doOnSubscribe() }
                .subscribe(onSuccess, onError)
        
        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}