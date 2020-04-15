package com.meteoro.githubarch.thread

import com.meteoro.githubarch.domain.thread.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AndroidMainThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}