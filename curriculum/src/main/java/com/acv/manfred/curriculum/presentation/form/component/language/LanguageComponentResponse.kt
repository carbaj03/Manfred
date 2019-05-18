package com.acv.manfred.curriculum.presentation.form.component.language

import com.acv.manfred.curriculum.domain.dto.LanguageDto
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.Id
import com.acv.manfred.curriculum.domain.model.Proficiency

data class LanguageComponentResponse(
    val id: GenerateId,
    var language: String,
    var proficiency: Proficiency
) : ComponentResponse {
    fun toDto(): LanguageDto =
        LanguageDto(id = id, language = language, proficency = proficiency)
}


interface ComponentResponse