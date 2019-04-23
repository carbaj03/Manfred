package com.acv.manfred.curriculum.domain

import com.acv.manfred.curriculum.domain.model.Questionnaire

typealias StepName = String

data class GetCvDto(
    val id: String, val stepName: StepName
)

data class RolesDto(
    val id: String, val stepName: StepName
)

data class ProficiencyDto(
    val id: String, val stepName: StepName
)

data class QuestionnaireDto(
    val questionnaires: List<Questionnaire>
)

object GetQuestionnaireDto