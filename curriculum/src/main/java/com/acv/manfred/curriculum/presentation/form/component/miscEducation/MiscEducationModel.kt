package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.presentation.form.component.common.*

data class MiscEducationModel(
    override val id: String? = null,
    var miscellaneous: String? = null,
    var componentType: ComponentType = New(NotModified)
) : ComponentModel

fun MiscEducationModel.toDomain(): MiscEducation =
    id?.let { MiscEducation(id = it, miscellaneous = miscellaneous) } ?: MiscEducation(miscellaneous = miscellaneous)

fun MiscEducation.toView(): MiscEducationModel =
    MiscEducationModel(id = id, miscellaneous = miscellaneous, componentType = Persisted(NotModified))