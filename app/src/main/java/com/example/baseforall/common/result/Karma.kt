package com.example.baseforall.common.result

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

sealed class Karma<out R> {

    data class Success<out T>(val data: T) : Karma<T>()
    data class Error(val error: Throwable) : Karma<Nothing>()
    object Loading : Karma<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Karma<*>.succeeded
    get() = this is Karma.Success && data != null

fun <T> Karma<T>.successOr(fallback: T): T {
    return (this as? Karma.Success<T>)?.data ?: fallback
}

val <T> Karma<T>.data: T?
    get() = (this as? Karma.Success)?.data

