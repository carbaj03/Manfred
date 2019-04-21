package com.acv.manfred.curriculum.domain.model

data class RoleCompany(
    var role: String,
    var from: String,
    var until: String,
    var challenges: Set<Challenge>
)