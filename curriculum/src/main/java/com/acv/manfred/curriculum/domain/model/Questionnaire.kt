package com.acv.manfred.curriculum.domain.model


data class Questionnaire(
    val id : GenerateId,
    val question: String,
    val answer: String
)