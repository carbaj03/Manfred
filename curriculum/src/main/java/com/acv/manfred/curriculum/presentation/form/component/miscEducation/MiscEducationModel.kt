package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentModel
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentType
import com.acv.manfred.curriculum.presentation.form.component.common.New
import com.acv.manfred.curriculum.presentation.form.component.common.NotModified

data class MiscEducationModel(
    override val id: GenerateId,
    var miscellaneous: String? = null,
    override var componentType: ComponentType = New(NotModified)
) : ComponentModel

fun MiscEducation.toView(): MiscEducationModel =
    MiscEducationModel(id = id, miscellaneous = miscellaneous, componentType = id.type())