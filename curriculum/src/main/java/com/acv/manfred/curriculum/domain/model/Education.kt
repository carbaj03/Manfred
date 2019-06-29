package com.acv.manfred.curriculum.domain.model

data class Education(
    val id: GenerateId,
    val institution: String,
    val study: String,
    val from: String,
    val until: String,
    val achievements: Set<String>? = null
)