package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.acv.manfred.curriculum.ui.common.WithActivity

interface Provider : WithActivity {
    fun viewModelProvider(factory: ViewModelProviderFactory): ViewModelProvider =
        when(factory){
            is EmptyViewModelFactory -> ViewModelProviders.of(baseActivity)
            is FormViewModelFactory ,
            is MiscEducationViewModelFactory ,
            is EducationViewModelFactory ,
            is LanguageViewModelFactory ,
            is QuestionaireViewModelFactory ,
            is ProfileViewModelFactory -> ViewModelProviders.of(baseActivity, factory)
        }
}

inline fun <reified T : ViewModel> Provider.viewModelProviders(
    factory: ViewModelProviderFactory = EmptyViewModelFactory
): T =
    viewModelProvider(factory).get(T::class.java)

