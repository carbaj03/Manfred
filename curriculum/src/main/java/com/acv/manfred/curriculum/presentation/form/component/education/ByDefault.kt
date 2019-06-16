package com.acv.manfred.curriculum.presentation.form.component.education

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault

data class EducationDefault(
    override val id: GenerateId,
    var institution: String,
    var study: String,
    var from: String,
    var until: String
) : ByDefault