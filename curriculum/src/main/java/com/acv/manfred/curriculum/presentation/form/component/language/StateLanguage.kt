package com.acv.manfred.curriculum.presentation.form.component.language


sealed class StateLanguage {
    object Load : StateLanguage()
    object Add : StateLanguage()
    data class Action(val componentAction: LanguageComponentAction) : StateLanguage()
}
