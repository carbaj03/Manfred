package com.acv.manfred.curriculum.data.gateway.datasource.local.model

data class ExperienceEntity(

    /**
     * Company in which you worked
     * (Required)
     *
     */
    var company: Set<CompanyEntity>
)