package com.acv.manfred.curriculum.presentation.form.component.questionnaire

import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.model.GenerateId

data class QuestionnaireComponentResponse(
    val id: GenerateId,
    var question: String,
    var answer: String
) : ComponentResponse {
    fun toDto(): QuestionnaireDto =
        QuestionnaireDto(id = id, question = question, answer = answer)
}

interface ComponentResponse