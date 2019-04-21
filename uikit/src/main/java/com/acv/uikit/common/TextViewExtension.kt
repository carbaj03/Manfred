package com.acv.uikit.common

import android.widget.TextView
import arrow.core.getOrElse
import com.acv.uikit.input.CompoundDrawable
import com.google.android.material.textfield.TextInputLayout

fun TextView.drawable(compoundDrawable: CompoundDrawable): Unit =
    setCompoundDrawablesWithIntrinsicBounds(
        compoundDrawable.leading.getOrElse { 0 },
        0,
        compoundDrawable.trailing.getOrElse { 0 },
        0
    )


fun TextInputLayout.drawable(compoundDrawable: CompoundDrawable): Unit {
    compoundDrawable.leading.map { setStartIconDrawable(it) }
    compoundDrawable.trailing.map { setEndIconDrawable(it) }
}
