package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import java.net.URI

data class ReferenceResponse(

    /**
     * Name of this reference
     * (Required)
     *
     */
    var name: String,
    /**
     * Role of this reference
     * (Required)
     *
     */
    var role: String,
    /**
     * Link to a profile of this reference
     *
     */
    var link: URI? = null,
    /**
     * Mail of this reference
     *
     */
    var mail: String? = null,
    /**
     * Phone Number of this reference with country code
     *
     */
    var phoneNumber: String? = null
)