package com.acv.manfred.curriculum.presentation.form.component.questionnaire

import com.acv.manfred.curriculum.domain.model.GenerateId

data class QuestionnaireDefault(
    override val id: GenerateId,
    var question: String,
    var answer: String
) : ByDefault

interface ByDefault{
    val id: GenerateId
}