package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.RoleCompany


data class RoleCompanyResponse(

    /**
     * Name of the role
     * (Required)
     *
     */
    var role: String,
    /**
     * When you started to work in the company
     * (Required)
     *
     */
    var from: String,
    /**
     * When you finished to work in the company
     * (Required)
     *
     */
    var until: String,
    /**
     * List of challenges of this role or title
     * (Required)
     *
     */
    var challenges: Set<ChallengeResponse>
) {
    fun toDomain() = RoleCompany(role, from, until, challenges.map { it.toDomain() }.toSet())
}