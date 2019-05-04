package com.acv.manfred.curriculum.ui.form

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.questionnaire.*

class QuestionaireViewModel(
    private val dependencies: UsesCasesIO
) : BaseViewModel(), UsesCasesIO by dependencies {

    val questionnaire by lazy { MutableLiveData<List<QuestionnaireModel>>() }

    val fab by lazy {  MutableLiveData<ComponentValidation>() }

    fun State.state(): Unit =
        when (this) {
            is Add -> addQuestionnaire()
            is Load -> getQuestionnaire()
            is Action -> componentAction.action()
        }

    fun ComponentAction.action(): Unit =
        when (this) {
            is Cancel -> { }
            is Remove -> remove(id)
            is Save -> save(item)
        }

    private fun getQuestionnaire() {
        GetQuestionnaireDto.allView().executeResult {
            fab.value = Valid
            questionnaire.value = it }
    }

    private fun addQuestionnaire() {
//        AddQuestionnaireDto(Questionnaire()).addView().executeResult(
//            error = { Log.e("sdf", it.message) },
//            success = { questionnaire.value = it })
        fab.value = Invalid
        questionnaire.value = questionnaire.value!!.plus(QuestionnaireModel())
    }

    private fun remove(id: String) {
        RemoveQuestionnaireDto(id).removeView().executeResult { questionnaire.value = it }
    }

    private fun save(questionaires: ComponentResponse) {
        QuestionnaireDto(questionaires).saveView().executeResult(
            error = { Log.e("sdf", it.message) },
            success = {
                Log.e("asdfds", it.toString())
                fab.value = Valid
                questionnaire.value = it
            }
        )
    }

}

sealed class State
object Load : State()
object Add : State()
data class Action(val componentAction: ComponentAction) : State()