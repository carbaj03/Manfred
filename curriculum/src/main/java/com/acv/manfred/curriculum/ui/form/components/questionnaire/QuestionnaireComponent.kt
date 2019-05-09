package com.acv.manfred.curriculum.ui.form.components.questionnaire

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.ui.form.components.common.*
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

    private val isCompleted
        get() = isModified is Completed

    private val isIncompleted
        get() = isModified is Incompleted

    init {
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.component_questionnaire, this, true)
        orientation = VERTICAL
    }

    override fun renderType(model: QuestionnaireModel): QuestionnaireComponent {
        val byDefault: ByDefault = createByDefault(model)
        state.value = if (model.componentType is Persisted) Valid else Invalid
        initActions()
        model.renderFields()
        model.renderType(byDefault)
        model.listener(byDefault)
        return this
    }

    private fun createByDefault(model: QuestionnaireModel): ByDefault =
        ByDefault(id = model.id, question = model.question ?: "", answer = model.answer ?: "")

    private fun QuestionnaireModel.renderFields() {
        inputQuestion.value = question ?: ""
        inputAnswer.value = answer ?: ""
        info.text = id
    }

    private fun initActions() {
        btnRemove.invisible()
        btnSave.invisible()
        btnCancel.invisible()
    }

    private fun QuestionnaireModel.listener(byDefault: ByDefault) {
        inputQuestion.listener(question, byDefault)
        inputAnswer.listener(answer, byDefault)
    }

    private fun Input.listener(toCompare: String?, byDefault: ByDefault) {
        watch(textWatcher {
            if (value == toCompare || isIncompleted) {
                this@QuestionnaireComponent.btnCancel.invisible()
                this@QuestionnaireComponent.btnSave.invisible()
            } else {
                this@QuestionnaireComponent.btnCancel.cancel(byDefault)
                this@QuestionnaireComponent.btnSave.save(byDefault.id)
            }
        })
    }

    private fun QuestionnaireModel.renderType(byDefault: ByDefault) =
        when (componentType) {
            is Persisted -> when (componentType.componentState) {
                Incompleted -> {
                    btnRemove.remove(id!!)
                    btnCancel.cancel(byDefault)
                }
                Completed -> {
                    btnRemove.remove(id!!)
                    btnSave.save(id)
                    btnCancel.cancel(byDefault)
                }
                NotModified -> btnRemove.remove(id!!)
            }
            is New -> when (componentType.componentState) {
                Incompleted -> btnCancel.cancel(byDefault)
                Completed -> {
                    btnSave.save(id)
                    btnCancel.cancel(byDefault)
                }
                NotModified -> {
                }
            }
        }

    private fun MaterialButton.remove(id: String): Unit =
        action { actions.value = Remove(id) }

    private fun MaterialButton.save(id: String?): Unit =
        action { actions.value = Save(createResponse(id)) }

    private fun createResponse(id: String?): QuestionnaireComponentResponse =
        QuestionnaireComponentResponse(
            id = id,
            answer = this@QuestionnaireComponent.inputAnswer.value,
            question = this@QuestionnaireComponent.inputQuestion.value
        )

    private fun MaterialButton.cancel(byDefault: ByDefault): Unit =
        action {
            this@QuestionnaireComponent.inputAnswer.value = byDefault.answer
            this@QuestionnaireComponent.inputQuestion.value = byDefault.question
            invisible()
        }

    private fun MaterialButton.action(f: () -> Unit) {
        visible()
        onClick { f() }
    }


}