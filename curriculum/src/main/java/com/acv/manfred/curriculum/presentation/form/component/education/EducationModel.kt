package com.acv.manfred.curriculum.presentation.form.component.education

import com.acv.manfred.curriculum.domain.model.Education
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentModel
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentType
import com.acv.manfred.curriculum.presentation.form.component.common.New
import com.acv.manfred.curriculum.presentation.form.component.common.NotModified

data class EducationModel(
    override val id: GenerateId,
    var institution: String,
    var study: String,
    var from: String,
    var until: String,
    var achievements: Set<String>? = null,
    override var componentType: ComponentType = New(NotModified)
) : ComponentModel

fun Education.toView(): EducationModel =
    EducationModel(
        id = id,
        institution = institution,
        study = study,
        from = from,
        until = until,
        achievements = achievements,
        componentType = id.type()
    )