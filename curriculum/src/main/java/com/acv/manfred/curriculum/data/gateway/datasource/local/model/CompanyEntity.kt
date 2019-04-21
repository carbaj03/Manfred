package com.acv.manfred.curriculum.data.gateway.datasource.local.model


data class CompanyEntity(
    var name: String,
    var roles: Set<RoleCompanyEntity>,
    var references: Set<ReferenceEntity>? = null
)