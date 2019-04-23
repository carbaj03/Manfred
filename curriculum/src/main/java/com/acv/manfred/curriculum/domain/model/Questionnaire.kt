package com.acv.manfred.curriculum.domain.model

import java.util.*


data class Questionnaire(
    var id : String = UUID.randomUUID().toString(),
    var question: String? = null,
    var answer: String? = null
)