package com.acv.manfred.curriculum.domain.dto

import com.acv.manfred.curriculum.domain.model.*

data class LanguageDto(
    val id: GenerateId,
    var language: String,
    var proficency: Proficiency
) {
    fun toDomain(): Language =
        when (id) {
            is WithId, Id -> Language(id = id, language = language, proficiency = proficency)
            NoId -> Language(id = Id, language = language, proficiency = proficency)
        }
}

object AddLanguageDto

data class RemoveLanguageDto(
    val id: GenerateId
)

object GetLanguageDto


object GetProficiencyDto