package com.example.letssopt.core.network.model

sealed class ApiError(
    val serverCode: String?,
    serverMessage: String?,
) : Throwable(serverMessage) {
    // 400
    class BadRequest(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    // 401
    class Unauthorized(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    // 403
    class Forbidden(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    // 404
    class NotFound(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    // 409
    class Conflict(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    // 500
    class InternalServerError(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    class Unknown(serverCode: String?, serverMessage: String?) : ApiError(serverCode, serverMessage)

    class NetworkConnection : ApiError(null, "Network connection failed")
}
