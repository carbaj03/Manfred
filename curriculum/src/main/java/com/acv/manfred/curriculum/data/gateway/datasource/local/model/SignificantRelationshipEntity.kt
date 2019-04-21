package com.acv.manfred.curriculum.data.gateway.datasource.local.model

data class SignificantRelationshipEntity(
    var name: String = "Name of this significant reference",
    var comment: String = "Describe what this significant reference provides you with",
    var role: String = "Role of this significant reference",
    var contact: String? = "Contact of this significant reference",
    var company: String? = "Name of the Company of this significant reference"
)
