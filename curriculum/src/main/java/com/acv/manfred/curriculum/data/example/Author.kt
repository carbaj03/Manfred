package com.acv.manfred.curriculum.data.example


/**
 * Main data of the CV author
 *
 */
data class Author(

    /**
     * Personal data of the CV author
     * (Required)
     *
     */
    var profile: Profile,
    /**
     * Explain to your future company what kind of professional you are
     * (Required)
     *
     */
    var intro: String,
    /**
     * List the goals that explain to your future company what kind of professional you'd like to be, what you'd like to be i.e., What motivates you
     *
     */
    var professionalGoals: Set<String>? = null,
    /**
     * List the skills not specifically related to your particular job or area of knowledge, but still can be used at work (for example, when you were manager in that Burger King and had to organize a team of employees)
     *
     */
    var transportableSkills: Set<String>? = null,
    /**
     * List the professional milestones that provided you with experience and knowledge for your future position
     *
     */
    var significantExperience: Set<String>? = null,
    /**
     * List the professional relationships (2-3 is enough) that you can seek advice in case you need, who provide you with professional insights in your day to day
     *
     */
    var significantRelationships: Set<SignificantRelationship>? = null
)
