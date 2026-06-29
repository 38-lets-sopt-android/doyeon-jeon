package com.example.letssopt.core.base

sealed interface Async<out T> {
    data object Init : Async<Nothing>

    data object Empty : Async<Nothing>

    data object Loading : Async<Nothing>

    data class Success<out T>(
        val data: T,
    ) : Async<T>
}
