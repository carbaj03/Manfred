package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.acv.manfred.curriculum.ui.common.WithActivity

interface Provider : WithActivity {
    fun viewModelProvider(factory: ViewModelProvider.Factory): ViewModelProvider =
        if (factory != EmptyViewModelFactory) ViewModelProviders.of(baseActivity, factory)
        else ViewModelProviders.of(baseActivity)
}

inline fun <reified T : ViewModel> Provider.viewModelProviders(factory: ViewModelProvider.Factory = EmptyViewModelFactory): T =
    viewModelProvider(factory).get(T::class.java)

