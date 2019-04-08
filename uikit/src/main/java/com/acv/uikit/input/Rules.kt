package com.acv.uikit.input

import android.text.InputFilter
import android.text.InputType.TYPE_CLASS_PHONE

typealias Rules = Input.() -> Unit

fun NoneRules(): Rules = {}

fun PhoneRules(): Rules = {
    et.inputType = TYPE_CLASS_PHONE
    et.filters = arrayOf(InputFilter.LengthFilter(9))
//    watch(textWatcher { if (it.isBlank()) itemRightInvisible() else itemRightVisible() })
}
