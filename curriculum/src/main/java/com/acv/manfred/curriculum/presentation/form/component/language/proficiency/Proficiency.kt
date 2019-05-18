package com.acv.manfred.curriculum.presentation.form.component.language.proficiency

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.uikit.adapterModel.POSITION

data class ProficiencyModel(
    override val id: GenerateId,
    val value: String,
    override val componentType: ComponentType
) : ComponentModel

fun ProficiencyModel.toDomain(): Proficiency =
    Proficiency(value)

fun Proficiency.toView(): ProficiencyModel =
    ProficiencyModel(id = id, value = value, componentType = Persisted(Completed))
