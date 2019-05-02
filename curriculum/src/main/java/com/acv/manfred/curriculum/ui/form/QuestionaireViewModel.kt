package com.acv.manfred.curriculum.ui.form

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentAction
import com.acv.manfred.curriculum.ui.form.components.common.Remove
import com.acv.manfred.curriculum.ui.form.components.common.Save

class QuestionaireViewModel(
    private val dependencies: UsesCasesIO
) : BaseViewModel(), UsesCasesIO by dependencies {

    val questionnaire by lazy { MutableLiveData<List<QuestionnaireModel>>() }

    fun getQuestionnaire() {
        GetQuestionnaireDto.allView().executeResult { questionnaire.value = it }
    }

    fun add() {
        questionnaire.value = questionnaire.value!!.plus(QuestionnaireModel("", "", "", Save(listOf())))
    }

    fun action(c: ComponentAction) = when (c) {
        is Remove -> remove(c.id)
        is Save -> save(c.items)
    }

    private fun remove(id: String) {
        RemoveQuestionnaireDto(id).removeView().executeResult { questionnaire.value = it }
    }

    private fun save(questionaires: List<QuestionnaireModel>) {
        QuestionnaireDto(questionaires).saveView().executeResult(
            error = { Log.e("sdf", it.message) },
            success = { Log.e("asdfds", it.toString()) }
        )
    }

}
