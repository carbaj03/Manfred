package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Company


data class CompanyResponse(

    /**
     * Name of the company
     * (Required)
     *
     */
    var name: String,
    /**
     * Your role or title during a period of time
     * (Required)
     *
     */
    var roles: Set<RoleCompanyResponse>,
    /**
     * List references of the company when you worked there
     *
     */
    var references: Set<ReferenceResponse>? = null
) {
    fun toDomain() = Company(name, roles.map { it.toDomain() }.toSet())
}