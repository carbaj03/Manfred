package com.acv.manfred.curriculum.ui.form.components.questionnaire

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import com.acv.uikit.common.textWatcher
import com.acv.uikit.input.Input
import com.acv.uikit.invisible
import com.acv.uikit.onClick
import com.acv.uikit.visible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_actions.view.*
import kotlinx.android.synthetic.main.component_questionnaire.view.*

class ActionsComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.component_actions, this)
    }

    fun invalid(): Unit =
        invisible(btnCancel, btnSave)

    private fun invisible(vararg views: View): Unit =
        views.hide()

    private fun Array<out View>.hide(): Unit =
        forEach { it.invisible() }

    fun enableRemove(f: () -> Unit): Unit =
        btnRemove.enabled(f)

    fun enableSave(f: () -> Unit): Unit =
        btnSave.enabled(f)

    fun disableCancel(f: () -> Unit): Unit =
        btnCancel.disabled(f)

    private fun MaterialButton.enabled(f: () -> Unit) {
        visible()
        onClick { f() }
    }

    private fun MaterialButton.disabled(f: () -> Unit) {
        invisible()
        onClick { f() }
    }
}