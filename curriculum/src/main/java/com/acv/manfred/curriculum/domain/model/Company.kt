package com.acv.manfred.curriculum.domain.model


data class Company(
    var name: String,
    var roles: Set<RoleCompany>,
    var references: Set<Reference>? = null
)