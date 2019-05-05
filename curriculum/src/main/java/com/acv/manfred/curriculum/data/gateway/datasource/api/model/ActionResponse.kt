package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Action


data class ActionResponse(

    /**
     * Sum up your run for this challenge
     * (Required)
     *
     */
    var summary: String,
    /**
     * List of tools, frameworks, languages you used or learnt
     *
     */
    var tools: String? = null
){
    fun toDomain() = Action(summary, tools)
}
