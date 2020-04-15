package com.meteoro.githubarch.thread

import com.meteoro.githubarch.domain.thread.ExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IOThread @Inject constructor() : ExecutionThread {

    override val scheduler: Scheduler = Schedulers.io()
}