package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault

data class MiscEducationDefault(
    override val id: GenerateId,
    var miscEducation: String
) : ByDefault