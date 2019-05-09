package com.acv.manfred.curriculum.domain.dto

import com.acv.manfred.curriculum.domain.model.Questionnaire

data class QuestionnaireDto(
    val id: String?,
    var question: String,
    var answer: String
){
    fun toDomain(): Questionnaire =
        id?.let { Questionnaire(id = it, question = question, answer = answer) } ?: Questionnaire(question = question, answer = answer)
}

data class AddQuestionnaireDto(
    val questionnaire: Questionnaire
)

data class RemoveQuestionnaireDto(
    val questionnaire: String
)

object GetQuestionnaireDto