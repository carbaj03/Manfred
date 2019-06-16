package com.acv.uikit.input

import arrow.core.Eval
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import com.acv.uikit.common.Model
import java.util.*

sealed class InputModel(
//    val type: InputViewType,
    val style: InputStyle,
    open val value: CharSequence,
    val f: Option<Eval<Unit>> = None,
    val rules: Rules
) : Model

data class TextModel(
    override val value: String
) : InputModel(InputText, value, None, TextRules())

data class DateModel(
    override val value: String,
    val action: () -> Unit
) : InputModel(InputDate, value, Eval.always { action() }.some(), DateRules())

data class SpinnerModel(
    override val value: String = "",
    val action: () -> Unit
) : InputModel(InputSpinner, value, Eval.always { action() }.some(), DateRules())

fun Calendar.format(): String =
    """${get(Calendar.DAY_OF_MONTH)} - ${get(Calendar.MONTH) + 1} - ${get(Calendar.YEAR)}"""