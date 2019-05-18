package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Language
import com.acv.manfred.curriculum.domain.model.NoId


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
    fun toDomain(): Language =
        Language(id = NoId, language = language, proficiency = proficiency.toDomain())
}
