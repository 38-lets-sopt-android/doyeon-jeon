package com.example.letssopt.core.network.util

import com.example.letssopt.core.common.util.suspendRunCatching
import com.example.letssopt.core.network.model.ApiError
import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.core.network.model.ErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ApiResponseHandler(
    private val json: Json,
) {
    suspend fun <T> safeApiCall(block: suspend () -> BaseResponse<T>): Result<T> =
        suspendRunCatching {
            val response = block()
            handleResponse(response)
        }.recoverCatching { throwable ->
            throw mapThrowable(throwable)
        }

    suspend fun safeUnitApiCall(block: suspend () -> BaseResponse<Unit>): Result<Unit> =
        suspendRunCatching {
            val response = block()
            handleUnitResponse(response)
        }.recoverCatching { throwable ->
            throw mapThrowable(throwable)
        }

    private fun <T> handleResponse(response: BaseResponse<T>): T {
        val success = response.success
        val data = response.data

        if (success) {
            return data ?: throw ApiError.Unknown(null, "Success but data is null")
        }

        val status = response.status
        val code = response.code
        val message = response.message

        throw mapApiError(status, code, message)
    }

    private fun handleUnitResponse(response: BaseResponse<Unit>) {
        val success = response.success

        if (success) return

        val status = response.status
        val code = response.code
        val message = response.message

        throw mapApiError(status, code, message)
    }

    private fun mapThrowable(throwable: Throwable): Throwable =
        when (throwable) {
            is HttpException -> parseHttpException(throwable)
            is UnknownHostException, is SocketTimeoutException -> ApiError.NetworkConnection()
            is ApiError -> throwable
            else -> ApiError.Unknown(null, throwable.message)
        }

    private fun parseHttpException(e: HttpException): ApiError {
        val errorBody = e.response()?.errorBody()?.use { it.string() }.orEmpty()
        val errorResponse = runCatching {
            json.decodeFromString<ErrorResponse>(errorBody)
        }.getOrNull()

        val status = errorResponse?.status ?: e.code()
        val code = errorResponse?.code
        val message = errorResponse?.message ?: e.message()

        return mapApiError(status, code, message)
    }

    private fun mapApiError(status: Int?, code: String?, message: String?): ApiError =
        when (status) {
            400 -> ApiError.BadRequest(code, message)
            401 -> ApiError.Unauthorized(code, message)
            403 -> ApiError.Forbidden(code, message)
            404 -> ApiError.NotFound(code, message)
            409 -> ApiError.Conflict(code, message)
            in 500..599 -> ApiError.InternalServerError(code, message)
            else -> ApiError.Unknown(code, message)
        }
}
