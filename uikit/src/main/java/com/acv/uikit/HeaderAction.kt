package com.acv.uikit

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import com.acv.uikit.common.click
import kotlinx.android.synthetic.main.header_action.view.*

class HeaderAction @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var text
        get() = tvTitle.text.toString()
        set(value) {
            tvTitle.text = value
        }

    var icon: Option<Drawable> = None
        set(value) {
            value.map { btnAction.icon = it }
            field = value
        }

    var textAction
        get() = btnAction.text.toString()
        set(value) {
            btnAction.text = value
        }

    init {
        inflate(context, R.layout.header_action, this)
        orientation = HORIZONTAL
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.HeaderAction, 0, 0)

        try {
            text = a.getString(R.styleable.HeaderAction_text) ?: ""
            textAction = a.getString(R.styleable.HeaderAction_textAction) ?: ""
            icon = a.getDrawable(R.styleable.HeaderAction_icon).some()
        } finally {
            a.recycle()
        }
    }

    fun action(f: () -> Unit): Unit =
        btnAction click { f() }
}