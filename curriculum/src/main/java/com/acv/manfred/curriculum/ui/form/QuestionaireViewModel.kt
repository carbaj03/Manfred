package com.acv.manfred.curriculum.ui.form

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.executeResult
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.StateQuestionnnaire
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.StateQuestionnnaire.*
import com.acv.manfred.curriculum.presentation.operation.QuestionnaireUsesCasesIO
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.*

class QuestionaireViewModel(
    private val dependencies: QuestionnaireUsesCasesIO
) : BaseViewModel(), QuestionnaireUsesCasesIO by dependencies {
    val questionnaire = MutableLiveData<List<QuestionnaireModel>>()
    val validation = MutableLiveData<ComponentValidation>()

    fun StateQuestionnnaire.run(): Unit =
        when (this) {
            is Add -> addQuestionnaire()
            is Load -> getQuestionnaire()
            is Action -> componentAction.run()
        }

    private fun QuestionnaireComponentAction.run(): Unit =
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

    private fun save(questionaires: QuestionnaireComponentResponse) {
        questionaires.toDto().saveView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                questionnaire.value = it
            }
        )
    }

}