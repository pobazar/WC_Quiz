package msk.pobazar.wcquiz.baseInteractor

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CompletableBaseInteractor : BaseInteractor() {
    internal abstract fun buildInteractor(): Completable
    
    fun execute(
            doOnSubscribe: () -> Unit = {},
            onComplete: () -> Unit,
            onError: ((t: Throwable) -> Unit)
    ) {
        disposeLast()
        lastDisposable = buildInteractor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { doOnSubscribe() }
                .subscribe(onComplete, onError)
        
        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}