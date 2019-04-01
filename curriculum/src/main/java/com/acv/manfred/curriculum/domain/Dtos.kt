package com.acv.manfred.curriculum.domain

typealias StepName = String

data class GetCvDto(
    val id: String, val stepName: StepName
)