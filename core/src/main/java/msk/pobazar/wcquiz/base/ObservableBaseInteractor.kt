package msk.pobazar.wcquiz.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class ObservableBaseInteractor<T> : BaseInteractor() {
    internal abstract fun buildInteractor(): Observable<T>
    
    fun execute(
            doOnSubscribe: () -> Unit = {},
            onNext: ((t: T) -> Unit),
            onSuccess: ((t: T) -> Unit),
            onError: ((t: Throwable) -> Unit)
    ) {
        disposeLast()
        lastDisposable = buildInteractor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { doOnSubscribe() }
                .doOnNext(onNext)
                .subscribe(onSuccess, onError)
        
        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}