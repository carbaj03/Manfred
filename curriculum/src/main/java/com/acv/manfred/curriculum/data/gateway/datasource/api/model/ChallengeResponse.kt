package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.Challenge

data class ChallengeResponse(

    /**
     * Sum up your challenge
     * (Required)
     *
     */
    var summary: String,
    /**
     * List of actions for this challenge
     * (Required)
     *
     */
    var actions: Set<ActionResponse>
) {
    fun toDomain() = Challenge(summary, actions.map { it.toDomain() }.toSet())
}