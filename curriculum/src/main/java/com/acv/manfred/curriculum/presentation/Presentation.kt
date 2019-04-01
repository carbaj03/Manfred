package com.acv.manfred.curriculum.presentation

import arrow.core.Some
import com.acv.manfredcv.domain.model.BaseError
import com.acv.manfredcv.domain.model.NotFoundError
import com.acv.manfredcv.domain.model.UnknownServerError


interface BaseView {
    fun catchError(baseError: BaseError)
}

interface LoadingView {
    fun hideLoading()
    fun showLoading()
}

fun exceptionAsCharacterError(e: BaseError): BaseError = NotFoundError

fun manageException(e: Throwable): BaseError = UnknownServerError(Some(e))