package com.example.multi_platform_android_project.Helper

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

inline fun <T> singleFlowOf(crossinline block: suspend () -> T) = flow { emit(block()) }

inline fun <T, E : Throwable> Flow<T>.mapError(crossinline block: suspend (Throwable) -> E) = catch { throw block(it) }

fun Throwable.mapToHttpError(): Throwable = when (val error = this) {
    is ResponseException ->
        when (error) {
            is RedirectResponseException -> {
                //3xx error here
                println()
                UnknownApiException("3xx error", error)
            }

            is ClientRequestException -> UnknownApiException("4xx error", error)
            is ServerResponseException -> {
                // Log the error, then return it wrapped in an UnknownApiException.
                UnknownApiException("5xx error", error)
            }

            else -> {
                UnknownApiException("Unknown error here", error)
            }
        }

    is ApiException -> error
    else -> {
        println("the actual api error")
        UnknownApiException(cause = error)
    }
}

/** The base class for error responses from the server */
@Suppress("UnnecessaryAbstractClass")
abstract class ApiException(
    message: String? = null,
    cause: Throwable? = null,
) : Error(message, cause)

/** An api call required an authentication but none was provided in the request */
class UnauthenticatedException(
    message: String = "No authentication provided",
    cause: Throwable? = null,
) : ApiException(message, cause)

/** An API call returned a conflict exception */
class ConflictException(
    message: String = "Conflict with API update",
    cause: Throwable? = null,
) : ApiException(message, cause)

/** A JSON:API error returned from an HTTP service, may have multiple error messages */
class JsonApiHttpException(
    val statusCode: Int,
    val errorDetails: List<ErrorDetail>,
    val path: String?,
    cause: Throwable,
) : ApiException("JSON:API HTTP Error", cause) {

    /**
     * Detailed HTTP error message which includes the response's [statusCode] and the original request [path], e.g.,
     * "/api/app/config/android".
     */
    val httpErrorMessage: String = "JSON:API HTTP $statusCode${path?.let { ": $it" }}"

    data class ErrorDetail(val code: String, val detail: String)
}

/** The server has returned an error response that is not handled by the application */
class UnknownApiException(
    message: String? = null,
    cause: Throwable? = null,
) : ApiException(message, cause)
