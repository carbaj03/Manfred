package com.acv.uikit.common

import android.widget.TextView
import arrow.core.getOrElse
import com.acv.uikit.input.CompoundDrawable

fun TextView.drawable(compoundDrawable: CompoundDrawable): Unit =
    setCompoundDrawablesWithIntrinsicBounds(compoundDrawable.leading.getOrElse { 0 }, 0, compoundDrawable.trailing.getOrElse { 0 }, 0)
