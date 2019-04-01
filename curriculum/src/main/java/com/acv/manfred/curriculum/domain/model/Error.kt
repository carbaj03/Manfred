package com.acv.manfred.curriculum.domain.model

import arrow.core.None
import arrow.core.Option

sealed class BaseError
object AuthenticationError : BaseError()
object NotFoundError : BaseError()
data class UnknownServerError(val e: Option<Throwable> = None) : BaseError()
data class ApiError(val e: Option<String> = None) : BaseError()
