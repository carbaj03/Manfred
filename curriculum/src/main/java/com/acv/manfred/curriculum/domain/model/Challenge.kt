package com.acv.manfred.curriculum.domain.model


data class Challenge(
    var summary: String,
    var actions: Set<Action>
)