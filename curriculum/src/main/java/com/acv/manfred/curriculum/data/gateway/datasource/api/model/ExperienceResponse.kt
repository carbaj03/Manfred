package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.data.mapSet
import com.acv.manfred.curriculum.domain.model.Experience


data class ExperienceResponse(

    /**
     * Company in which you worked
     * (Required)
     *
     */
    var company: Set<CompanyResponse>
) {
    fun toDomain() = Experience(company.mapSet { it.toDomain() })
}
