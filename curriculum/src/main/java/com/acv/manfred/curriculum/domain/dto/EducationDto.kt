package com.acv.manfred.curriculum.domain.dto

import com.acv.manfred.curriculum.domain.model.*

data class EducationDto(
    val id: GenerateId,
    var institution: String,
    var study: String,
    var from: String,
    var until: String
) {
    fun toDomain(): Education =
        when (id) {
            is WithId, Id -> Education(id = id, institution = institution, study = study, from = from, until = until)
            NoId -> Education(id = Id, institution = institution, study = study, from = from, until = until)
        }
}

object AddEducationDto

data class RemoveEducationDto(
    val miscEducation: GenerateId
)

object GetEducationDto