package com.acv.manfred.curriculum.presentation

import com.acv.manfredcv.domain.model.BaseError

interface ScreenState
object empty : ScreenState

sealed class Transaction
object Idle : Transaction()
object Loading : Transaction()
data class Success(val screenState: ScreenState = empty) : Transaction()
data class Failure(val baseError: BaseError) : Transaction()
data class WaitingForRetry(val seconds: Int) : Transaction()