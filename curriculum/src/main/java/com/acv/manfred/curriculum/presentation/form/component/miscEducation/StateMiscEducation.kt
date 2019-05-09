package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import com.acv.manfred.curriculum.presentation.form.component.common.MiscEducationComponentAction

sealed class StateMiscEducation {
    object Load : StateMiscEducation()
    object Add : StateMiscEducation()
    data class Action(val componentAction: MiscEducationComponentAction) : StateMiscEducation()
}
