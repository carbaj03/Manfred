package com.acv.manfred.curriculum.data.example


data class Questionnaire(

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
)