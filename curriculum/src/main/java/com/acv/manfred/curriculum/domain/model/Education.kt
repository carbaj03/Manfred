package com.acv.manfred.curriculum.domain.model

data class Education(
    var id: GenerateId,
    var institution: String,
    var study: String,
    var from: String,
    var until: String,
    var achievements: Set<String>? = null
)