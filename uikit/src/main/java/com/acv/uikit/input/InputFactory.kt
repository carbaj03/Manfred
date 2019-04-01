package com.fintonic.uikit.input

import com.acv.uikit.input.InputDate
import com.acv.uikit.input.InputText

fun Int.idToStyle() = when (this) {
    InputDate.id -> InputDate
    InputText.id -> InputText
    else -> InputText
}