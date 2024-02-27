package org.example

sealed class CreateFormFailure {
    data object InvalidUrl : CreateFormFailure()

    data object LocationHeaderNotPresent : CreateFormFailure()
    data class RequestNotExecuted(val exception: Throwable): CreateFormFailure()
    data class ErrorResponse(val code: Int, val body: String?) : CreateFormFailure()
}
