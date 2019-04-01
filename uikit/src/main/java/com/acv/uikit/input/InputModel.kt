package com.acv.uikit.input

import arrow.core.Eval
import arrow.core.None
import arrow.core.Option
import com.acv.uikit.common.Model

data class InputModel(
    val style: InputStyle,
    val value: CharSequence,
    val f: Option<Eval<Unit>> = None
) : Model