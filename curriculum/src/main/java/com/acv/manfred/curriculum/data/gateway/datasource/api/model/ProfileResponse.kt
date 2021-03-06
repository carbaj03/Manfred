package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import arrow.core.toOption
import com.acv.manfred.curriculum.domain.model.NoId
import com.acv.manfred.curriculum.domain.model.Profile
import java.net.URI


/**
 * Personal data of the CV author
 *
 */
data class ProfileResponse(

    /**
     * Complete name of the CV author
     * (Required)
     *
     */
    var name: String,
    /**
     * URL of the author image
     *
     */
    var image: URI? = null,
    /**
     * Birthday in format yyyy-mm-dd
     * (Required)
     *
     */
    var birthday: String,
    /**
     * List the links to code repositories, social media, professional networks or any other worthy public profile
     *
     */
    var publicLinks: Set<URI>? = null,
    /**
     * The ones that better describe your previous or future positions
     * (Required)
     *
     */
    var roles: Set<RoleProfileResponse>,
    /**
     * Total number of years of professional experience
     * (Required)
     *
     */
    var yearsOfExperience: Float
) {
    fun toDomain(): Profile =
        Profile(
            id = NoId,
            name = name,
            image = image?.path.toOption(),
            birthday = birthday,
            publicLinks = publicLinks?.map { it.path } ?: emptyList(),
            roles = roles.map { it.toDomain() },
            yearsOfExperience = yearsOfExperience
        )
}
