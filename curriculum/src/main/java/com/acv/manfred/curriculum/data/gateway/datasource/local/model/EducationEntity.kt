package com.acv.manfred.curriculum.data.gateway.datasource.local.model

data class EducationEntity(
    var institution: String,
    var study: String,
    var from: String,
    var until: String,
    var achievements: Set<String>? = null
)