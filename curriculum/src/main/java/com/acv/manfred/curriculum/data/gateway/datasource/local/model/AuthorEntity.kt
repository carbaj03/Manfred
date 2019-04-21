package com.acv.manfred.curriculum.data.gateway.datasource.local.model


data class AuthorEntity(
    var profile: ProfileEntity,
    var intro: String,
    var professionalGoals: Set<String>? = null,
    var transportableSkills: Set<String>? = null,
    var significantExperience: Set<String>? = null,
    var significantRelationships: Set<SignificantRelationshipEntity>? = null
)
