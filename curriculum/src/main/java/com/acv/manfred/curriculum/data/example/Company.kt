package com.acv.manfred.curriculum.data.example

import com.acv.manfred.curriculum.data.example.Reference
import com.acv.manfred.curriculum.data.example.RoleCompany

data class Company(

    /**
     * Name of the company
     * (Required)
     *
     */
    var name: String,
    /**
     * Your role or title during a period of time
     * (Required)
     *
     */
    var roles: Set<RoleCompany>,
    /**
     * List references of the company when you worked there
     *
     */
    var references: Set<Reference>? = null
)