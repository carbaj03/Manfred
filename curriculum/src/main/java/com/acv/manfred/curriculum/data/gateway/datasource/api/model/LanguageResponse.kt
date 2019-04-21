package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Language


data class LanguageResponse(

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
    var proficiency: ProficiencyResponse
) {
    fun toDomain() = Language(language, proficiency.toDomain())
}
