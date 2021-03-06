package com.acv.uikit.input

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import arrow.core.*
import com.acv.uikit.R
import com.acv.uikit.common.EditMode
import com.acv.uikit.common.Errorable
import com.acv.uikit.common.Component
import com.acv.uikit.common.drawable
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class Input @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr), Errorable, Component<InputModel>, EditMode {

    private var model: Option<InputModel> = None

    var value
        get() = et.text.toString()
        set(value) = et.setText(value)

    val et get() = editText!!

    init {
        init(isInEditMode)
    }

    override fun editMode() {
        addView(TextInputEditText(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        })
        attrs.obtainStyle(context).getInt(R.styleable.Input_input_style, InputDate.id)
    }

    override fun normalMode() {
        addView(TextInputEditText(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        })
//        model.style.render()
        model.applyStyle(attrs, context)
    }

    override fun errorK(value: String) {
        this.error = value
    }

    override fun render(model: InputModel): Input {
        this.model = model.some()
        model.apply()
        return this
    }

    private fun InputModel.apply() {
        editText!!.setText(value)
        editText!!.inputType = InputType.TYPE_CLASS_NUMBER
        style.render()
        f.map { action ->
            editText!!.isFocusableInTouchMode = false

            isClickable = true
            isFocusable = true
            editText!!.isClickable = false
            editText!!.isFocusable = false
            editText!!.setOnClickListener { action.value() }
        }
    }

    private fun Option<InputModel>.applyStyle(attrs: AttributeSet?, context: Context): Unit =
        fold(
            ifEmpty = { attrs.getStyle(context).map { style -> style.render() } },
            ifSome = { style -> style.style.render() })


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

    private fun InputStyle.render() {
        compoundDrawable.map { drawable(it) }
    }

    fun watch(f: TextWatcher): Unit =
        et.addTextChangedListener(f)

    fun disableAction() {
//        setEndIconOnClickListener { et.text.clear() }
    }

    fun enableAction() {
//        setEndIconOnClickListener { et.text.clear() }
    }
}

data class CompoundDrawable(
    val leading: Option<Int> = None,
    val trailing: Option<Int> = None
)