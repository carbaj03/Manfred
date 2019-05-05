package com.acv.manfred.curriculum.ui.form

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.common.Error
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import com.acv.manfred.curriculum.ui.form.components.questionnaire.*

class QuestionaireViewModel(
    private val dependencies: UsesCasesIO
) : BaseViewModel(), UsesCasesIO by dependencies {
    val questionnaire = MutableLiveData<List<QuestionnaireModel>>()
    val validation = MutableLiveData<ComponentValidation>()

    fun State.run(): Unit =
        when (this) {
            is Add -> addQuestionnaire()
            is Load -> getQuestionnaire()
            is Action -> componentAction.run()
        }

    private fun ComponentAction.run(): Unit =
        when (this) {
            is Cancel -> {
            }
            is Remove -> remove(id)
            is Save -> save(item)
        }

    private fun addQuestionnaire() {
        validation.value = Invalid
        questionnaire.value = questionnaire.value!!.plus(QuestionnaireModel())
        Log.e("trhead", Thread.currentThread().name)
    }

//    private fun addQuestionnaire() {
//        AddQuestionnaireDto(Questionnaire()).addView().executeResult(
//            error = { validation.value = Error(it.error) },
//            success = {
//                validation.value = Valid
//                questionnaire.value = it
//            })
//    }

    private fun getQuestionnaire() {
        GetQuestionnaireDto.allView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                questionnaire.value = it
            })
    }

    private fun remove(id: String) {
        RemoveQuestionnaireDto(id).removeView().executeResult(
            error = { validation.value = Error(it.error) },
            success = { questionnaire.value = it }
        )
    }

    private fun save(questionaires: ComponentResponse) {
        QuestionnaireDto(questionaires).saveView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                questionnaire.value = it
            }
        )
    }

}

sealed class State
object Load : State()
object Add : State()
data class Action(val componentAction: ComponentAction) : State()