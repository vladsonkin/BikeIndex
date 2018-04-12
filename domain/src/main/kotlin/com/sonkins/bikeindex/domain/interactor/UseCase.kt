package com.sonkins.bikeindex.domain.interactor

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vlad Sonkin
 * on 16 March 2018.
 *
 * Abstract class for a UseCase that returns an instance of a [Observable].
 */
abstract class UseCase<T, in Params> {

    /**
     * Builds a [Observable] which will be used when the current [UseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: Params): Observable<T>

    /**
     * Executes the current use case.
     */
    open fun execute(params: Params): Observable<T> {
        return this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
    }

}