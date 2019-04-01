package com.acv.uikit.input

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.TextView
import arrow.core.*
import com.acv.uikit.R
import com.acv.uikit.common.EditMode
import com.acv.uikit.common.Errorable
import com.acv.uikit.common.Component
import com.fintonic.uikit.input.idToStyle
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Input @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val style: Option<InputStyle> = None
) : TextInputLayout(context, attrs, defStyleAttr), Errorable, Component<InputModel>, EditMode {

    var value
        get() = editText!!.text.toString()
        set(value) {
            editText!!.setText(value)
        }

    init {
        init(isInEditMode)
    }

    override fun editMode() {
        addView(TextInputEditText(context))
        attrs.obtainStyle(context).getInt(R.styleable.Input_input_style, InputText.id)
    }

    override fun normalMode() {
        addView(TextInputEditText(context))
        style.applyStyle(attrs, context)
    }

    private fun Option<InputStyle>.applyStyle(attrs: AttributeSet?, context: Context): Unit =
        fold(
            ifEmpty = { attrs.getStyle(context).map { style -> style.render() } },
            ifSome = { style -> style.render() })


    private fun AttributeSet?.getStyle(context: Context): Option<InputStyle> =
        toOption()
            .map { attr -> attr.obtainStyle(context) }
            .filter { it.getInt(R.styleable.Input_input_style, -1) >= 0 }
            .map { it.getStyle() }

    private fun AttributeSet?.obtainStyle(context: Context): TypedArray =
        context.obtainStyledAttributes(this, R.styleable.Input)

    private fun TypedArray.getStyle(): InputStyle =
        try {
            getInt(R.styleable.Input_input_style, InputText.id).idToStyle()
        } catch (e: Throwable) {
            InputText.id.idToStyle()
        } finally {
            recycle()
        }


    override fun render(model: InputModel):Input =
        apply { model.apply()}

    private fun InputModel.apply() {
        editText!!.setText(value)
        style.render()
        f.map { action ->
            editText!!.isFocusableInTouchMode = false

            isClickable = true
            isFocusable = true
            editText!!.isClickable = false
            editText!!.isFocusable = false
            setOnClickListener { action.extract() }
        }
    }

    private fun InputStyle.render() {
        compoundDrawable.map { editText!!.drawable(it) }
    }

    override fun errorK(value: String) {
        this.error = value
    }

}

data class CompoundDrawable(
    val leading: Option<Int>,
    val trailing: Option<Int>
)

fun TextView.drawable(
    compoundDrawable: CompoundDrawable
) = setCompoundDrawablesWithIntrinsicBounds(compoundDrawable.leading.getOrElse { 0 },
    0,
    compoundDrawable.trailing.getOrElse { 0 },
    0
)
