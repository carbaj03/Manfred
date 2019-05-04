package com.acv.manfred.curriculum.ui.form.components.questionnaire

import com.acv.manfred.curriculum.domain.model.Questionnaire

data class QuestionnaireModel(
    val id: String? = null,
    var question: String? = null,
    var answer: String? = null,
    var componentType: ComponentType = New(NotModified)
//    var actions: List<ComponentAction> = emptyList()
)

fun QuestionnaireModel.toDomain(): Questionnaire =
    id?.let { Questionnaire(id = it, question = question, answer = answer) } ?: Questionnaire(question = question, answer = answer)

fun Questionnaire.toView(): QuestionnaireModel =
    QuestionnaireModel(id = id, question = question, answer = answer, componentType = Persisted(NotModified))