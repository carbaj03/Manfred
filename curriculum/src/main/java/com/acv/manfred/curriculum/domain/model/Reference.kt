package com.acv.manfred.curriculum.domain.model

import java.net.URI

data class Reference(
    var name: String,
    var role: String,
    var link: URI? = null,
    var mail: String? = null,
    var phoneNumber: String? = null
)