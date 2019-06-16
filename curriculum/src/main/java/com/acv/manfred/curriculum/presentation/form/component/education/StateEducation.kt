package com.acv.manfred.curriculum.presentation.form.component.education

sealed class StateEducation {
    object Load : StateEducation()
    object Add : StateEducation()
    data class Action(val componentAction: EducationComponentAction) : StateEducation()
}
