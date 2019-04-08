package com.acv.uikit.input

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import com.acv.uikit.R
import com.fintonic.uikit.common.Style

sealed class InputStyle(
    val id: Int,
    val compoundDrawable: Option<CompoundDrawable>
) : Style

object InputText : InputStyle(1000, None)
object InputDate : InputStyle(1001, CompoundDrawable(R.drawable.ic_event.some(), R.drawable.ic_arrow_drop_down.some()).some())
