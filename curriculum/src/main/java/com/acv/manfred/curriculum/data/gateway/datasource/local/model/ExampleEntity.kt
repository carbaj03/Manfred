package com.acv.manfred.curriculum.data.gateway.datasource.local.model



data class ExampleEntity(
    var author: AuthorEntity,
    var experience: Set<ExperienceEntity>,
    var education: Set<EducationEntity>,
    var languages: Set<LanguageEntity>? = null,
    var miscEducation: Set<String>? = null,
    var questionnaire: Set<QuestionnaireEntity>? = null
)

