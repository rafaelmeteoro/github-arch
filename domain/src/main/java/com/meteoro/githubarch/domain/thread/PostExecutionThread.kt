package com.meteoro.githubarch.domain.thread

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}