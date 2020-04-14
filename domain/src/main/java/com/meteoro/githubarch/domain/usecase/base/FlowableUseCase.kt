package com.meteoro.githubarch.domain.usecase.base

import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.observers.DisposableObserver

abstract class FlowableUseCase<T, in Params>(
    private val postExecutionThread: PostExecutionThread,
    private val executionThread: ExecutionThread
) : DisposableUseCase() {

    abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {
        val observable = buildUseCaseObservable(params)
            .toObservable()
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))
    }
}