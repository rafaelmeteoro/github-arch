package com.meteoro.githubarch.domain.thread

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}