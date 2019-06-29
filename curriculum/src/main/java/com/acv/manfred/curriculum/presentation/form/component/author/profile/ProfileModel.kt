package com.acv.manfred.curriculum.presentation.form.component.author.profile

import arrow.core.Option
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.Profile
import com.acv.manfred.curriculum.domain.model.RoleProfile
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentModel
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentType
import com.acv.manfred.curriculum.presentation.form.component.common.New
import com.acv.manfred.curriculum.presentation.form.component.common.NotModified
import java.net.URI

data class ProfileModel(
    override val id: GenerateId,
    val name: String,
    val image: Option<URI>,
    val birthday: String,
    val publicLinks: List<URI>,
    val roles: List<RoleProfile>,
    val yearsOfExperience: Float,
    override val componentType: ComponentType = New(NotModified)
) : ComponentModel

fun Profile.toView(): ProfileModel =
    ProfileModel(
        id = id,
        name = name,
        image = image.map { URI(it) },
        birthday = birthday,
        publicLinks = publicLinks.map { URI(it) },
        roles = roles,
        yearsOfExperience = yearsOfExperience,
        componentType = id.type()
    )
