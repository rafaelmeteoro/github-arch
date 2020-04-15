package com.meteoro.githubarch.presentation.mapper

interface UIMapper<out UI, in D> {
    fun mapToView(domain: D): UI
}