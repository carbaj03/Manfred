package com.acv.manfred.curriculum.data.gateway.datasource.api.model

import com.acv.manfred.curriculum.data.mapSet
import com.acv.manfred.curriculum.data.mapSetPosition
import com.acv.manfred.curriculum.domain.model.Example


/**
 * Manfred Awesomic CV
 *
 *
 * An open CV format
 *
 */
data class ExampleResponse(

    /**
     * Main data of the CV author
     * (Required)
     *
     */
    var author: AuthorResponse,
    /**
     * List of working experience
     * (Required)
     *
     */
    var experience: Set<ExperienceResponse>,
    /**
     * Degree or kind of schooling
     * (Required)
     *
     */
    var education: Set<EducationResponse>,
    /**
     * List of languages and your proficiency level
     *
     */
    var languages: Set<LanguageResponse>? = null,
    /**
     * Miscellaneous education such as courses, certifications, awards, projects, etc.
     *
     */
    var miscEducation: Set<String>? = null,
    /**
     * List of questions to help understanding if your motivations match with those of your potential employer
     *
     */
    var questionnaire: Set<QuestionnaireResponse>? = null
) {
    fun toDomain(): Example = Example(
        author.toDomain(),
        experience.mapSet { it.toDomain() },
        education.mapSet { it.toDomain() },
        languages?.mapSet { it.toDomain() },
        miscEducation,
        questionnaire?.mapSet { it.toDomain() }
    )
}

