package com.acv.manfred.curriculum.ui.form.components.questionnaire

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
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
import kotlinx.android.synthetic.main.component_questionnaire.view.*

class QuestionnaireComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Validable, Actionable<QuestionnaireComponentAction>, Component<QuestionnaireModel> {
    override val state: ObservableValidation = MutableLiveData()
    override val actions: MutableLiveData<QuestionnaireComponentAction> = MutableLiveData()

    private val Input.isValid: Boolean
        get() = value.isNotBlank()

    private val Input.isInvalid: Boolean
        get() = value.isBlank()

    private val isModified
        get() = when {
            inputQuestion.isValid && inputAnswer.isValid -> Completed
            else -> Incompleted
        }

    private var answerC
        get() = inputAnswer.value
        set(value) {
            inputAnswer.value = value
        }

    private var questionC
        get() = inputQuestion.value
        set(value) {
            inputQuestion.value = value
        }

    private val isCompleted
        get() = isModified is Completed

    private val isIncompleted
        get() = isModified is Incompleted

    init {
        inflate(context, R.layout.component_questionnaire, this)
        orientation = VERTICAL
    }

    override fun renderType(model: QuestionnaireModel): QuestionnaireComponent {
        state.value = if (model.componentType is Persisted) Valid else Invalid
        model.renderFields()
        model.renderType()
        model.listener()
        return this
    }

    private fun QuestionnaireModel.createByDefault(): ByDefault =
        ByDefault(id = id, question = question, answer = answer)

    private fun QuestionnaireModel.renderFields() {
        questionC = question
        answerC = answer
        info.text = id.id
    }

    private fun invisible(vararg views: View): Unit =
        views.forEach { it.invisible() }

    private fun QuestionnaireModel.listener() {
        inputQuestion.listener { if (it == question || isIncompleted) invalid() else valid(createByDefault()) }
        inputAnswer.listener { if (it == answer || isIncompleted) invalid() else valid(createByDefault()) }
    }

    private fun Input.listener(toWatch: (String) -> Unit): Unit =
        watch(textWatcher { toWatch(it.toString()) })

    private fun valid(byDefault: ByDefault) {
        btnCancel.enableCancel(byDefault)
        btnSave.enableSave(byDefault.id)
    }

    private fun invalid(): Unit =
        invisible(btnCancel, btnSave)

    private fun QuestionnaireModel.renderType() =
        when (componentType) {
            is Persisted -> persistedState()
            is New -> newState()
        }

    private fun QuestionnaireModel.newState() =
        when (componentType.componentState) {
            Incompleted -> {
                btnCancel.enableCancel(createByDefault())
                invisible(btnRemove, btnSave)
            }
            Completed -> {
                btnSave.enableSave(id)
                btnCancel.enableCancel(createByDefault())
                btnRemove.invisible()
            }
            NotModified -> {
                invisible(btnRemove, btnSave, btnCancel)
            }
        }

    private fun QuestionnaireModel.persistedState() =
        when (componentType.componentState) {
            Incompleted -> {
                btnRemove.enableRemove(id)
                btnCancel.enableCancel(createByDefault())
                btnSave.invisible()
            }
            Completed -> {
                btnRemove.enableRemove(id)
                btnSave.enableSave(id)
                btnCancel.enableCancel(createByDefault())
            }
            NotModified -> {
                btnRemove.enableRemove(id)
                invisible(btnCancel, btnSave)
            }
        }

    private fun MaterialButton.enableRemove(id: GenerateId): Unit =
        enabled { actions.value = Remove(id) }

    private fun MaterialButton.enableSave(id: GenerateId): Unit =
        enabled { actions.value = createSave(id) }

    private fun MaterialButton.enableCancel(byDefault: ByDefault): Unit =
        enabled {
            answerC = byDefault.answer
            questionC = byDefault.question
            invisible()
        }

    private fun MaterialButton.enabled(f: () -> Unit) {
        visible()
        onClick { f() }
    }

    private fun createSave(id: GenerateId): Save =
        Save(QuestionnaireComponentResponse(id = id, answer = answerC, question = questionC))

}