package com.acv.manfred.curriculum.ui.form.components.common

import androidx.lifecycle.MediatorLiveData
import com.acv.manfred.curriculum.ui.form.components.questionnaire.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.questionnaire.Invalid
import com.acv.manfred.curriculum.ui.form.components.questionnaire.ObservableValidation
import com.acv.manfred.curriculum.ui.form.components.questionnaire.Valid


typealias MediatorState = MediatorLiveData<ComponentValidation>


fun ObservableValidation.valid() {
    value = Valid
}

fun ObservableValidation.invalid() {
    value = Invalid
}