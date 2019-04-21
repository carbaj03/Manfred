package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import com.acv.manfred.curriculum.domain.model.Challenge

data class RoleCompanyEntity(
    var role: String,
    var from: String,
    var until: String,
    var challenges: Set<Challenge>
)