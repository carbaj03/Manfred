package com.acv.manfred.curriculum.domain.model


data class Author(
    var profile: Profile,
    var intro: String,
    var professionalGoals: Set<String>? = null,
    var transportableSkills: Set<String>? = null,
    var significantExperience: Set<String>? = null,
    var significantRelationships: Set<SignificantRelationship>? = null
)
