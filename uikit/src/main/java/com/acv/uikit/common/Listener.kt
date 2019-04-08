package com.acv.uikit.common

import android.text.Editable
import android.text.TextWatcher

fun textWatcher(
    beforeTextChanged: () -> Unit = {},
    afterTextChanged: (Editable) -> Unit = {},
    onTextChanged: (CharSequence) -> Unit = {}
): TextWatcher =
    object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) =
            beforeTextChanged()

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) =
            onTextChanged(s)

        override fun afterTextChanged(s: Editable) =
            afterTextChanged(s)
    }