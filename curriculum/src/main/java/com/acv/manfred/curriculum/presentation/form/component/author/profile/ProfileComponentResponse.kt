package com.acv.manfred.curriculum.presentation.form.component.author.profile

import arrow.core.Option
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.RoleProfile
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentResponse
import java.net.URI

data class ProfileComponentResponse(
    val id: GenerateId,
    val name: String,
    val image: Option<URI>,
    val birthday: String,
    val publicLinks: List<URI>,
    val roles: List<RoleProfile>,
    val yearsOfExperience: Float
) : ComponentResponse {
    fun toDto(): ProfileDto =
        ProfileDto(
            id = id,
            name = name,
            image = image,
            birthday = birthday,
            publicLinks = publicLinks,
            roles = roles,
            yearsOfExperience = yearsOfExperience
        )
}
