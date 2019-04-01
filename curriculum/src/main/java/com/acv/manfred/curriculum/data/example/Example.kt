package com.acv.manfred.curriculum.data.example


/**
 * Manfred Awesomic CV
 *
 *
 * An open CV format
 *
 */
data class Example(

    /**
     * Main data of the CV author
     * (Required)
     *
     */
    var author: Author,
    /**
     * List of working experience
     * (Required)
     *
     */
    var experience: Set<Experience>,
    /**
     * Degree or kind of schooling
     * (Required)
     *
     */
    var education: Set<Education>,
    /**
     * List of languages and your proficiency level
     *
     */
    var languages: Set<Language>? = null,
    /**
     * Miscellaneous education such as courses, certifications, awards, projects, etc.
     *
     */
    var miscEducation: Set<String>? = null,
    /**
     * List of questions to help understanding if your motivations match with those of your potential employer
     *
     */
    var questionnaire: Set<Questionnaire>? = null
)