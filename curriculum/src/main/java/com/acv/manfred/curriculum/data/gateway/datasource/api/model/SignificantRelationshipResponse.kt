package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.domain.model.SignificantRelationship


data class SignificantRelationshipResponse(
    var name: String = "Name of this significant reference",
    var comment: String = "Describe what this significant reference provides you with",
    var role: String = "Role of this significant reference",
    var contact: String? = "Contact of this significant reference",
    var company: String? = "Name of the Company of this significant reference"
) {
    fun toDomain() = SignificantRelationship(name, comment, role, contact, company)
}
