package com.acv.manfred.curriculum.domain.dto

import com.acv.manfred.curriculum.domain.model.*

data class QuestionnaireDto(
    val id: GenerateId,
    val question: String,
    val answer: String
) {
    fun toDomain(): Questionnaire =
        Questionnaire(id = id.id(), question = question, answer = answer)

    private fun GenerateId.id(): GenerateId =
        when (this) {
            is WithId, Id -> this
            NoId -> Id
        }

}

object AddQuestionnaireDto

data class RemoveQuestionnaireDto(
    val questionnaire: GenerateId
)

object GetQuestionnaireDto