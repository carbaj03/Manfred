package com.acv.manfred.curriculum.domain.model

import arrow.core.None
import arrow.core.Option
import arrow.core.getOrElse

sealed class BaseError(open val error: String) : Throwable()
object AuthenticationError : BaseError("Auth")
object NotFoundError : BaseError("Not Foud")
data class UnknownServerError(val e: Option<String> = None) : BaseError(e.getOrElse { "Unknown Error" })
data class ApiError(val e: Option<String> = None) : BaseError(e.getOrElse { "Api Error" })
