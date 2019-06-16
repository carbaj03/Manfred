package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acv.manfred.curriculum.domain.gategay.GatewayIO
import com.acv.manfred.curriculum.presentation.operation.EducationUseCase
import com.acv.manfred.curriculum.presentation.operation.LanguageUsesCasesIO
import com.acv.manfred.curriculum.presentation.operation.MiscEducationUseCaseIO
import com.acv.manfred.curriculum.presentation.operation.QuestionnaireUsesCasesIO
import com.acv.manfred.curriculum.ui.form.FormViewModel
import com.acv.manfred.curriculum.ui.form.components.education.EducationViewModel
import com.acv.manfred.curriculum.ui.form.components.miscEducation.MiscEducationViewModel
import com.acv.manfred.curriculum.ui.form.languaje.LanguageViewModel
import com.acv.manfred.curriculum.ui.form.questionnaire.QuestionnaireViewModel

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

class MiscEducationViewModelFactory(
    private val dependencies: MiscEducationUseCaseIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MiscEducationViewModel::class.java)) {
            return MiscEducationViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class EducationViewModelFactory(
    private val dependencies: EducationUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EducationViewModel::class.java)) {
            return EducationViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class LanguageViewModelFactory(
    private val dependencies: LanguageUsesCasesIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LanguageViewModel::class.java)) {
            return LanguageViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class QuestionaireViewModelFactory(
    private val dependencies: QuestionnaireUsesCasesIO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionnaireViewModel::class.java)) {
            return QuestionnaireViewModel(dependencies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

object EmptyViewModel : BaseViewModel()