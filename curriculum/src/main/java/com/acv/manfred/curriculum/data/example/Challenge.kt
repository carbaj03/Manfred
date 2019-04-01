package com.acv.manfred.curriculum.data.example

data class Challenge(

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
    var actions: Set<Action>
)