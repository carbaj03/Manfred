package com.acv.uikit

import android.os.Build
import android.util.TypedValue
import android.view.View


fun View.setRippleNormal(): Unit =
    TypedValue().run {
        context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }

fun View.setRipple(): Unit =
    TypedValue().run {
        when {
            isLollipop() -> context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
            else -> context.theme.resolveAttribute(R.attr.selectableItemBackgroundBorderless, this, true)
        }
        setBackgroundResource(resourceId)
    }

fun isLollipop(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

infix fun <T : View> T.onClick(f: (T) -> Unit) =
    setOnClickListener { f(this) }


