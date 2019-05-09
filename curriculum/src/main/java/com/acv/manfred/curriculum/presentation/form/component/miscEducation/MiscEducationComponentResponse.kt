package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ComponentResponse

data class MiscEducationComponentResponse(
    val id: String?,
    var miscEducation: String
) : ComponentResponse {
    fun toDto(): MiscEducationDto =
        MiscEducationDto(id = id, miscellaneous = miscEducation)
}
