package com.acv.manfred.curriculum.presentation.form.component.questionnaire

import com.acv.manfred.curriculum.domain.model.*
import com.acv.manfred.curriculum.presentation.form.component.common.*

data class QuestionnaireModel(
    override val id: GenerateId,
    var question: String,
    var answer: String,
    override val componentType: ComponentType = New(NotModified)
) : ComponentModel

fun Questionnaire.toView(): QuestionnaireModel =
    QuestionnaireModel(id = id, question = question, answer = answer, componentType = id.type())