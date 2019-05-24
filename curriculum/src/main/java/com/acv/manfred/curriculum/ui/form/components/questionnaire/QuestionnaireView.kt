package com.acv.manfred.curriculum.ui.form.components.questionnaire

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.QuestionnaireResponse
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.*
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

class QuestionnaireView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr),
    Actionable<QuestionnaireComponentAction>,
    QuestionnaireComponent {

    init {
        inflate(context, R.layout.component_questionnaire, this)
        orientation = VERTICAL
    }

    override val state: ObservableValidation = MutableLiveData()

    override val actions: MutableLiveData<QuestionnaireComponentAction> = MutableLiveData()

    override val isModified
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

    override fun QuestionnaireModel.createByDefault(): QuestionnaireDefault =
        QuestionnaireDefault(id = id, question = question, answer = answer)

    override fun QuestionnaireModel.renderFields() {
        questionC = question
        answerC = answer
        info.text = id.id
    }

    override fun QuestionnaireModel.listener() {
        inputQuestion.listener { if (it == question || isIncompleted) invalid() else valid(createByDefault()) }
        inputAnswer.listener { if (it == answer || isIncompleted) invalid() else valid(createByDefault()) }
    }

    override fun valid(byDefault: QuestionnaireDefault) {
        btnCancel.enableCancel(byDefault)
        btnSave.enableSave(byDefault)
    }

    override fun invalid(): Unit =
        invisible(btnCancel, btnSave)

    override fun QuestionnaireModel.newIncompled() {
        btnCancel.enableCancel(createByDefault())
        invisible(btnRemove, btnSave)
    }

    override fun QuestionnaireModel.newCompleted() {
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnRemove.invisible()
    }

    override fun QuestionnaireModel.newNotModified() {
        invisible(btnRemove, btnSave, btnCancel)
    }

    override fun QuestionnaireModel.persistedIncompled() {
        btnRemove.enableRemove(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnSave.invisible()
    }

    override fun QuestionnaireModel.persistedCompleted() {
        btnRemove.enableRemove(createByDefault())
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
    }

    override fun QuestionnaireModel.persistedNotModified() {
        btnRemove.enableRemove(createByDefault())
        invisible(btnCancel, btnSave)
    }

    override fun MaterialButton.enableRemove(model: QuestionnaireDefault): Unit =
        enabled { actions.value = Remove(model.id) }

    override fun MaterialButton.enableSave(model: QuestionnaireDefault): Unit =
        enabled { actions.value = Save(model.createResponse()) }

    override fun MaterialButton.enableCancel(byDefault: QuestionnaireDefault): Unit =
        enabled {
            answerC = byDefault.answer
            questionC = byDefault.question
            invisible()
        }

    override fun QuestionnaireDefault.createResponse(): QuestionnaireComponentResponse =
        QuestionnaireComponentResponse(id = id, answer = answerC, question = questionC)

}