package com.acv.manfred.curriculum.presentation.form.component.common

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentResponse


typealias MiscEducationObservableAction = MutableLiveData<MiscEducationComponentAction>

sealed class MiscEducationComponentAction : ComponentAction {
    data class Cancel(val id: String) : MiscEducationComponentAction()
    data class Remove(val id: String) : MiscEducationComponentAction()
    data class Save(val item: MiscEducationComponentResponse) : MiscEducationComponentAction()
}
