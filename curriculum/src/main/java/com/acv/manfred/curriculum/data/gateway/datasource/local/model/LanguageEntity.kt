package com.acv.manfred.curriculum.data.gateway.datasource.local.model



data class LanguageEntity(

    /**
     * Language
     * (Required)
     *
     */
    var language: String,
    /**
     * Your level of this language proficiency
     * (Required)
     *
     */
    var proficiency: ProficiencyEntity
)
