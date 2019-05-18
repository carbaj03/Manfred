package com.acv.manfred.curriculum.domain.dto

typealias StepName = String

data class GetCvDto(
    val id: String, val stepName: StepName
)

data class RolesDto(
    val id: String, val stepName: StepName
)
