package com.acv.manfred.curriculum.presentation.form.component.author.profile

import arrow.core.Option
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.RoleProfile
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault
import java.net.URI

data class ProfileDefault(
    override val id: GenerateId,
    val name: String,
    val image: Option<URI>,
    val birthday: String,
    val publicLinks: List<URI>,
    val roles: List<RoleProfile>,
    val yearsOfExperience: Float
) : ByDefault