package com.acv.manfred.curriculum.domain.dto

import arrow.core.Option
import com.acv.manfred.curriculum.domain.model.*
import java.net.URI

data class ProfileDto(
    val id: GenerateId,
    val name: String,
    val image: Option<URI>,
    val birthday: String,
    val publicLinks: List<URI>,
    val roles: List<RoleProfile>,
    val yearsOfExperience: Float
) {
    fun toDomain(): Profile =
        Profile(
            id = generateId(),
            name = name,
            image = image.map { it.path },
            birthday = birthday,
            publicLinks = publicLinks.map { it.path },
            roles = roles.toList(),
            yearsOfExperience = yearsOfExperience
        )

    private fun generateId(): GenerateId =
        when (id) {
            is WithId, Id -> id
            NoId -> Id
        }
}

object GetProfileDto
object AddProfileDto
data class RemoveProfileDto(val id: GenerateId)

