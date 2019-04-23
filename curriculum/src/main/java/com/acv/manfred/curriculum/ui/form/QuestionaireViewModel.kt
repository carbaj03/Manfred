package com.acv.manfred.curriculum.ui.form

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel

class QuestionaireViewModel(
    private val dependencies: GatewayIO
) : BaseViewModel(), GatewayIO by dependencies {

    val questionnaire by lazy { MutableLiveData<List<Questionnaire>>() }

    fun getQuestionnaire() {
        GetQuestionnaireDto.all().executeResult { questionnaire.value = it }
    }

    fun save(questionaires: List<Questionnaire>) {
        QuestionnaireDto(questionaires).save().executeResult(
            error = { Log.e("sdf", it.message) },
            success = { Log.e("asdfds", it.toString()) }
        )

    }

}
