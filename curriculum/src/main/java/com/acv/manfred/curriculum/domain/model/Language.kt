package com.acv.manfred.curriculum.domain.model

data class Language(

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
    var proficiency: Proficiency
)
