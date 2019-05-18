package com.acv.manfred.curriculum.domain.model


data class Questionnaire(
    var id : GenerateId,
    var question: String,
    var answer: String
)