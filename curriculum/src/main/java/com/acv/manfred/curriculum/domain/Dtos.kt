package com.acv.manfred.curriculum.domain

typealias StepName = String

data class GetCvDto(
    val id: String, val stepName: StepName
)

data class RolesDto(
    val id: String, val stepName: StepName
)

data class ProficiencyDto(
    val id: String, val stepName: StepName
)