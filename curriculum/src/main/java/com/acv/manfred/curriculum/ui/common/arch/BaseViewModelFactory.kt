package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acv.manfred.curriculum.domain.GatewayIO
import com.acv.manfred.curriculum.domain.UsesCasesIO
import com.acv.manfred.curriculum.ui.form.FormViewModel
import com.acv.manfred.curriculum.ui.form.LanguageViewModel
import com.acv.manfred.curriculum.ui.form.QuestionaireViewModel

object EmptyViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmptyViewModel::class.java)) {
            return EmptyViewModel as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class FormViewModelFactory(
    private val dependencies: GatewayIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class LanguageViewModelFactory(
    private val dependencies: GatewayIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            return LanguageViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class QuestionaireViewModelFactory(
    private val dependencies: UsesCasesIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionaireViewModel::class.java)) {
            return QuestionaireViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

object EmptyViewModel : BaseViewModel()

class Creator(
    private val dependencies: UsesCasesIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionaireViewModel::class.java)) {
            return QuestionaireViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}