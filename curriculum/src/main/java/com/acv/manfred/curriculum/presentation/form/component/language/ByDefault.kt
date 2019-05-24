package com.acv.manfred.curriculum.presentation.form.component.language

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault

data class LanguageDefault(
    override val id: GenerateId,
    val language: String,
    val proficiency: String,
    val proficiencies : List<ProficiencyModel>
) : ByDefault