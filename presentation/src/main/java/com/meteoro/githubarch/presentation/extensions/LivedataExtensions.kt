package com.meteoro.githubarch.presentation.extensions

import androidx.lifecycle.MutableLiveData

/**
 * Use this functions to create LiveData with initialized values instead of repeating
 * apply funcion each time or initialize the values in classes's init blocks
 * */
fun <T : Any> mutableLivedataOf(initialValue: T? = null): MutableLiveData<T> {
    return MutableLiveData<T>().apply { value = initialValue }
}