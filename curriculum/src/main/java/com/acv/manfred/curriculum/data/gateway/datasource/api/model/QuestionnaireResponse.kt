package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Questionnaire


data class QuestionnaireResponse(

    /**
     * Question to find out anything you understand is important for the open position
     *
     */
    var question: String? = null,
    /**
     * Open answer to the formulated question
     *
     */
    var answer: String? = null
) {
    fun toDomain(): Questionnaire =
        Questionnaire(question = question, answer = answer)
}