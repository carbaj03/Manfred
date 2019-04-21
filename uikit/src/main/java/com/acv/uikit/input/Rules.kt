package com.acv.uikit.input

import android.text.InputFilter
import android.text.InputType
import android.text.InputType.TYPE_CLASS_PHONE
import com.acv.uikit.common.textWatcher
import com.acv.uikit.onClick
import com.acv.uikit.setRippleNormal

typealias Rules = Input.() -> Unit

fun NoneRules(): Rules = {}

fun PhoneRules(): Rules = {
    et.inputType = TYPE_CLASS_PHONE
    et.filters = arrayOf(InputFilter.LengthFilter(9))
//    watch(textWatcher { if (it.isBlank()) itemRightInvisible() else itemRightVisible() })
}

fun DateRules(): Rules = {
    et.inputType = InputType.TYPE_CLASS_DATETIME
    et.isClickable = true
    et.isFocusable = false
    setRippleNormal()
//    et onClick { right() }
}

fun SpinnerRules(): Rules = {
    et.inputType = InputType.TYPE_CLASS_TEXT
    et.isClickable = true
    et.isFocusable = false
    setRippleNormal()
//    et onClick { right() }
}

fun TextRules(): Rules = {
    et.inputType = InputType.TYPE_CLASS_TEXT
    disableAction()
    watch(textWatcher { if (it.isBlank()) disableAction() else enableAction() })
}

fun TextMultiLineRules(): Rules = {
    et.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
//    itemRightInvisible()
//    watch(textWatcher { if (it.isBlank()) itemRightInvisible() else itemRightVisible() })
}