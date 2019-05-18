package com.acv.manfred.curriculum.domain.dto

import com.acv.manfred.curriculum.domain.model.*

data class MiscEducationDto(
    val id: GenerateId,
    var miscellaneous: String
) {
    fun toDomain(): MiscEducation =
        when (id) {
            is WithId, Id -> MiscEducation(id = id, miscellaneous = miscellaneous)
            NoId -> MiscEducation(id = Id, miscellaneous = miscellaneous)
        }
}

object AddMiscEducationDto

data class RemoveMiscEducationDto(
    val miscEducation: GenerateId
)

object GetMiscEducationDto