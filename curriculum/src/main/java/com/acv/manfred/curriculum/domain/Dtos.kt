package com.acv.manfred.curriculum.domain

import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireModel

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
    val questionnaires: List<QuestionnaireModel>
)

data class AddQuestionnaireDto(
    val questionnaires: Questionnaire
)

data class RemoveQuestionnaireDto(
    val questionnaire: String
)

object GetQuestionnaireDto