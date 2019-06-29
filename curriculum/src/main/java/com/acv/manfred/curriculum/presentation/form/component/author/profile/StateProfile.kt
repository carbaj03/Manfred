package com.acv.manfred.curriculum.presentation.form.component.author.profile

sealed class StateProfile {
    object Load : StateProfile()
    object Add : StateProfile()
    data class Action(val componentAction: ProfileComponentAction) : StateProfile()
}
