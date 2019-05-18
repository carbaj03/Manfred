package com.acv.manfred.curriculum.presentation.form.component.language

import com.acv.manfred.curriculum.domain.model.*
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel

data class LanguageModel(
    override val id: GenerateId,
    var language: String,
    var proficiency: String,
    var proficiencies: List<ProficiencyModel> = emptyList(),
    override var componentType: ComponentType = New(NotModified)
) : ComponentModel

fun Language.toView(proficiencies: List<ProficiencyModel>): LanguageModel =
    LanguageModel(id = id, language = language, proficiency = proficiency.value, proficiencies = proficiencies, componentType = Persisted(NotModified))

fun Language.toView(): LanguageModel =
    LanguageModel(id = id, language = language, proficiency = proficiency.value, componentType = id.type())
