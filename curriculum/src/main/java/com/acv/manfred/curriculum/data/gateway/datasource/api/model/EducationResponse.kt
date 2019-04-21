package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Education


data class EducationResponse(

    /**
     * School or University
     * (Required)
     *
     */
    var institution: String,
    /**
     * Degree/field of study
     * (Required)
     *
     */
    var study: String,
    /**
     * When you started your studies
     * (Required)
     *
     */
    var from: String,
    /**
     * When you finished your studies
     * (Required)
     *
     */
    var until: String,
    /**
     * List of awards or achievements during your studies
     *
     */
    var achievements: Set<String>? = null
){
    fun toDomain() = Education(institution, study, from, until, achievements)
}