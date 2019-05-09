package com.acv.manfred.curriculum.domain.model

import java.util.*


data class MiscEducation(
    var id : String = UUID.randomUUID().toString(),
    var miscellaneous: String? = null
)