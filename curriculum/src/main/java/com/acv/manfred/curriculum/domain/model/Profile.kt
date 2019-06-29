package com.acv.manfred.curriculum.domain.model

import arrow.core.Option

data class Profile(
    val id: GenerateId,
    val name: String,
    val image: Option<String>,
    val birthday: String,
    val publicLinks: List<String>,
    val roles: List<RoleProfile>,
    val yearsOfExperience: Float
)
