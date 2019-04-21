package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import java.net.URI

data class ReferenceEntity(
    var name: String,
    var role: String,
    var link: URI? = null,
    var mail: String? = null,
    var phoneNumber: String? = null
)