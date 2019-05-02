package com.acv.manfred.curriculum.ui.form.components.common

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData


typealias ObservableState = MutableLiveData<ComponentState>
typealias ObservableAction = MutableLiveData<ComponentAction>
typealias MediatorState = MediatorLiveData<ComponentState>


fun ObservableState.valid() {
    value = Valid
}

fun ObservableState.invalid() {
    value = Invalid
}