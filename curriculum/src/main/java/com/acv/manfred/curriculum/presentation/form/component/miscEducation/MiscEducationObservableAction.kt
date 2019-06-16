package com.acv.manfred.curriculum.presentation.form.component.miscEducation

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentAction


typealias MiscEducationObservableAction = MutableLiveData<MiscEducationComponentAction>

sealed class MiscEducationComponentAction : ComponentAction {
    data class Cancel(val id: GenerateId) : MiscEducationComponentAction()
    data class Remove(val id: GenerateId) : MiscEducationComponentAction()
    data class Save(val item: MiscEducationComponentResponse) : MiscEducationComponentAction()

//    data class MiscEducationChange(val item: String) : MiscEducationComponentAction()
}
