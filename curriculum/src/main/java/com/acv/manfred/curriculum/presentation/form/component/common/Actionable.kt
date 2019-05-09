package com.acv.manfred.curriculum.presentation.form.component.common

import androidx.lifecycle.MutableLiveData



interface ComponentAction

interface Actionable<A>: ComponentAction {
    val actions:  MutableLiveData<A>
}