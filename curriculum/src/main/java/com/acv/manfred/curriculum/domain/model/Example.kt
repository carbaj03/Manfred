package com.acv.manfred.curriculum.domain.model

data class Example(
    var author: Author,
    var experience: Set<Experience>,
    var education: Set<Education>,
    var languages: Set<Language>? = null,
    var miscEducation: Set<String>? = null,
    var questionnaire: Set<Questionnaire>? = null
)

