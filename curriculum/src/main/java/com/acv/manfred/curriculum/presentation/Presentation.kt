package com.acv.manfred.curriculum.presentation

import com.acv.manfred.curriculum.domain.model.BaseError


interface BaseView {
    fun catchError(baseError: BaseError)
}