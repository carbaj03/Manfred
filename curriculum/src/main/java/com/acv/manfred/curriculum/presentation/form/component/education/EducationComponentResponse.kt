package com.acv.manfred.curriculum.presentation.form.component.education

import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentResponse

data class EducationComponentResponse(
    val id: GenerateId,
    var institution: String,
    var study: String,
    var from: String,
    var until: String
) : ComponentResponse {
    fun toDto(): EducationDto =
        EducationDto(
            id = id,
            institution = institution,
            study = study,
            from = from,
            until = until
        )
}
