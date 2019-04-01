package com.acv.manfredcv.domain.model

import arrow.core.None
import arrow.core.Option

sealed class BaseError
object AuthenticationError : BaseError()
object NotFoundError : BaseError()
data class UnknownServerError(val e: Option<Throwable> = None) : BaseError()
