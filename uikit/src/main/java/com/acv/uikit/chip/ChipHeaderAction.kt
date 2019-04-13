package com.acv.uikit.chip

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import arrow.core.None
import arrow.core.Option
import arrow.core.some
import com.acv.uikit.R
import com.acv.uikit.common.Errorable
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.chip_header.view.*


class ChipHeaderAction @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr), Errorable {
    override fun errorK(value: String) {
        error = value
    }

    var text
        get() = headerAction.text
        set(value) {
            headerAction.text = value
        }

//    var error
//        get() = tvError.text
//        set(value) {
//            tvError.text = value
//        }

    var textAction
        get() = headerAction.textAction
        set(value) {
            headerAction.textAction = value
        }

    var icon : Option<Drawable> = None
        set(value) {
            headerAction.icon = value
            field = value
        }

    fun swap(newItems: List<ChipModel>, compare: (ChipModel, ChipModel) -> Boolean) {
        chipView.swap(newItems, compare)
    }

    init {
        inflate(context, R.layout.chip_header, this)
        orientation = VERTICAL

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ChipHeaderAction, 0, 0)

        try {
            text = a.getString(R.styleable.ChipHeaderAction_text) ?: ""
            error = a.getString(R.styleable.ChipHeaderAction_error)
            textAction = a.getString(R.styleable.ChipHeaderAction_textAction) ?: ""
            icon = a.getDrawable(R.styleable.ChipHeaderAction_icon).some()
        } finally {
            a.recycle()
        }
    }

    fun action(f: (View) -> Unit): Unit =
            headerAction.action { f(it) }
}