package com.acv.manfred.curriculum.ui.form.components.questionnaire

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.uikit.common.textWatcher
import com.acv.uikit.input.Input
import com.acv.uikit.invisible
import com.acv.uikit.onClick
import com.acv.uikit.visible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_questionnaire.view.*

typealias ObservableValidation = MutableLiveData<ComponentValidation>

interface Validable {
    val state: ObservableValidation
}

sealed class ComponentValidation
object Valid : ComponentValidation()
object Invalid : ComponentValidation()

typealias ObservableType = MutableLiveData<ComponentType>

sealed class ComponentType(open val componentState: ComponentState)
data class Persisted(override val componentState: ComponentState) : ComponentType(componentState)
data class New(override val componentState: ComponentState) : ComponentType(componentState)


typealias ObservableState = MutableLiveData<ComponentState>

sealed class ComponentState
object NotModified : ComponentState()
sealed class Modified : ComponentState()
object Incompleted : Modified()
object Completed : Modified()


typealias ObservableAction = MutableLiveData<ComponentAction>

sealed class ComponentAction
data class Cancel(val id: String) : ComponentAction()
data class Remove(val id: String) : ComponentAction()
data class Save(val items: List<QuestionnaireModel>) : ComponentAction()

class QuestionnaireComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) , Validable {
    override val state: ObservableValidation = MutableLiveData()
    val actions: ObservableAction = MutableLiveData()
    var type: ObservableType = MutableLiveData()
//    val state: ObservableState = MutableLiveData()

    private var model = QuestionnaireModel()

    private val questionState
        get() = if (inputQuestion.value.isBlank()) Invalid else Valid

    private val answerState
        get() = if (inputAnswer.value.isBlank()) Invalid else Valid

    private fun isModified() =
        when {
            questionState is Valid && answerState is Invalid -> Completed
            else -> Incompleted
        }

    init {
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.component_questionnaire, this, true)
        orientation = VERTICAL
    }

    fun render(model: QuestionnaireModel): QuestionnaireComponent {
        this.model = model
        inputQuestion.value = model.question ?: ""
        inputAnswer.value = model.answer ?: ""
        initActions()
        state.value = if (model.componentType is Persisted) Valid else Invalid
//        model.actions.render()
        model.componentType.render()
        listener()
        return this
    }

    private fun initActions() {
        btnRemove.invisible()
        btnSave.invisible()
        btnCancel.invisible()
    }

    private fun listener() {
        inputQuestion.listener(model.question)
        inputAnswer.listener(model.answer)
    }

    private fun Input.listener(toCompare: String?) {
        watch(textWatcher {
            if (value == toCompare || isModified() is Completed) {
                this@QuestionnaireComponent.btnCancel.invisible()
                this@QuestionnaireComponent.btnSave.invisible()
            } else {
                this@QuestionnaireComponent.btnCancel.cancel()
                this@QuestionnaireComponent.btnSave.save()
            }
        })
    }

    private fun List<ComponentAction>.render(): Unit = forEach {
        when (it) {
            is Remove -> btnRemove.remove()
            is Save -> btnSave.save()
            is Cancel -> btnCancel.cancel()
        }
    }

    private fun ComponentType.render() =
        when (this) {
            is Persisted -> when (componentState) {
                Incompleted -> {
                    btnRemove.remove()
                    btnCancel.cancel()
                }
                Completed -> {
                    btnRemove.remove()
                    btnSave.save()
                    btnCancel.cancel()
                }
                NotModified -> btnRemove.remove()
            }
            is New -> when (componentState) {
                Incompleted -> btnCancel.cancel()
                Completed -> {
                    btnSave.save()
                    btnCancel.cancel()
                }
                NotModified -> {
                }
            }
        }

    private fun MaterialButton.remove(): Unit =
        action { actions.value = Remove(model.id!!) }

    private fun MaterialButton.save(): Unit =
        action {
            model.answer = this@QuestionnaireComponent.inputAnswer.value
            model.question = this@QuestionnaireComponent.inputQuestion.value
            actions.value = Save(listOf(model))
        }

    private fun MaterialButton.cancel(): Unit =
        action {
            this@QuestionnaireComponent.inputAnswer.value = model.answer ?: ""
            this@QuestionnaireComponent.inputQuestion.value = model.question ?: ""
            invisible()
        }

    private fun MaterialButton.action(f: () -> Unit) {
        visible()
        onClick { f() }
    }
}