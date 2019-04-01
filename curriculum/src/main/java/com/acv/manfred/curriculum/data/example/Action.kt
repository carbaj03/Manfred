package com.acv.manfred.curriculum.data.example


data class Action(

    /**
     * Sum up your action for this challenge
     * (Required)
     *
     */
    var summary: String,
    /**
     * List of tools, frameworks, languages you used or learnt
     *
     */
    var tools: String? = null
)
