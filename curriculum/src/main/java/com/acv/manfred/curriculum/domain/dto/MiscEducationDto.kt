package com.acv.manfred.curriculum.domain.dto

import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.domain.model.Questionnaire

data class MiscEducationDto(
    val id: String?,
    var miscellaneous: String
) {
    fun toDomain(): MiscEducation =
        id?.let { MiscEducation(id = it, miscellaneous = miscellaneous) } ?: MiscEducation(miscellaneous = miscellaneous)
}

data class AddMiscEducationDto(
    val miscEducation: MiscEducation
)

data class RemoveMiscEducationDto(
    val miscEducation: String
)

object GetMiscEducationDto