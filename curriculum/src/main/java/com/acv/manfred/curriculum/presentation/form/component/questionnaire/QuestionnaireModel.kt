package com.acv.manfred.curriculum.presentation.form.component.questionnaire

import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.presentation.form.component.common.*

data class QuestionnaireModel(
    override val id: String? = null,
    var question: String? = null,
    var answer: String? = null,
    var componentType: ComponentType = New(NotModified)
) : ComponentModel

fun QuestionnaireModel.toDomain(): Questionnaire =
    id?.let { Questionnaire(id = it, question = question, answer = answer) } ?: Questionnaire(question = question, answer = answer)

fun Questionnaire.toView(): QuestionnaireModel =
    QuestionnaireModel(id = id, question = question, answer = answer, componentType = Persisted(NotModified))