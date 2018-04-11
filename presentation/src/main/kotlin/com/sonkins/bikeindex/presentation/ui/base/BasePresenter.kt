package com.sonkins.bikeindex.presentation.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Vlad Sonkin
 * on 29 March 2018.
 */
abstract class BasePresenter<out T : MvpView>(open val view: T)
    : MvpPresenter, LifecycleObserver {

    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onAttach() {
        (view as LifecycleOwner).lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onDetach() {
        disposables.dispose()
    }

    override fun showError(exception: Throwable) {
        view.showError(exception.message!!)
    }

    override fun showLoading() {
        view.showLoading()
    }

    override fun hideLoading() {
        view.hideLoading()
    }

    fun unsubscribeOnDestroy(disposable: Disposable) {
        disposables.add(disposable)
    }
}