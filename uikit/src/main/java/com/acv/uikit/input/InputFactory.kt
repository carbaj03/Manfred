package com.acv.uikit.input

fun Int.idToStyle(): InputStyle =
    when (this) {
        InputDate.id -> InputDate
        InputText.id -> InputText
        InputSpinner.id -> InputSpinner
        else -> InputText
    }