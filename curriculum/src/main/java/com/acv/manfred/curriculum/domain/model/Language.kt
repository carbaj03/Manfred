package com.acv.manfred.curriculum.domain.model

data class Language(
    var id : GenerateId,
    var language: String,
    var proficiency: Proficiency
)
