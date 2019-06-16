package com.acv.manfred.curriculum.presentation.form.component.miscEducation

sealed class StateMiscEducation {
    object Load : StateMiscEducation()
    object Add : StateMiscEducation()
    data class Action(val componentAction: MiscEducationComponentAction) : StateMiscEducation()
}
