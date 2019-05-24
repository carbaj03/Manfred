package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentResponse

data class MiscEducationComponentResponse(
    val id: GenerateId,
    var miscEducation: String
) : ComponentResponse {
    fun toDto(): MiscEducationDto =
        MiscEducationDto(id = id, miscellaneous = miscEducation)
}
