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

//class AssetViewModelFactory(private val id: IdAsset) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AssetViewModel::class.java)) {
//            return AssetViewModel(id) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

object EmptyViewModel : BaseViewModel()