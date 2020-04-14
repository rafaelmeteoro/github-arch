package com.meteoro.githubarch.domain.usecase.base

import com.meteoro.githubarch.domain.thread.ExecutionThread
import com.meteoro.githubarch.domain.thread.PostExecutionThread
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<T, in Params> constructor(
    private val postExecutionThread: PostExecutionThread,
    private val executionThread: ExecutionThread
) : DisposableUseCase() {

    abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params? = null) {
        val single = buildUseCaseSingle(params)
            .subscribeOn(executionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(single.subscribeWith(observer))
    }
}