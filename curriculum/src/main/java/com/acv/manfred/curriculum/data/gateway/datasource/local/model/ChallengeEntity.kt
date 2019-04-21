package com.acv.manfred.curriculum.data.gateway.datasource.local.model


data class ChallengeEntity(
    var summary: String,
    var actions: Set<ActionEntity>
)