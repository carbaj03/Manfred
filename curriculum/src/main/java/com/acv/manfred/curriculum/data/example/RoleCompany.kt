package com.acv.manfred.curriculum.data.example

data class RoleCompany(

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
    var challenges: Set<Challenge>
)