package com.app.core.domain.util

sealed class APIState<out T : Any> {
    class DataStat<T : Any>(val value: T) : APIState<T>()
    class ErrorState(val throwable: Throwable) : APIState<Nothing>()
    object LoadingState : APIState<Nothing>()
}