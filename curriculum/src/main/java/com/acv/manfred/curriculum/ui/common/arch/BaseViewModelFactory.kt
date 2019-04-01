package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object EmptyViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmptyViewModel::class.java)) {
            return EmptyViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

object EmptyViewModel : BaseViewModel()