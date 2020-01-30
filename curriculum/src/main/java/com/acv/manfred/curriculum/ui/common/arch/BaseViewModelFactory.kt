package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acv.manfred.curriculum.domain.gategay.GatewayIO
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileViewModel
import com.acv.manfred.curriculum.presentation.operation.*
import com.acv.manfred.curriculum.ui.form.FormViewModel
import com.acv.manfred.curriculum.ui.form.components.education.EducationViewModel
import com.acv.manfred.curriculum.ui.form.components.miscEducation.MiscEducationViewModel
import com.acv.manfred.curriculum.ui.form.languaje.LanguageViewModel
import com.acv.manfred.curriculum.ui.form.questionnaire.QuestionnaireViewModel


sealed class ViewModelProviderFactory : ViewModelProvider.Factory {
    abstract val model: BaseViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(model::class.java)) {
            return model as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

object EmptyViewModelFactory : ViewModelProviderFactory() {
    override val model: BaseViewModel = EmptyViewModel
}

class FormViewModelFactory(
    dependencies: GatewayIO
) : ViewModelProviderFactory() {
    override val model: BaseViewModel = FormViewModel(dependencies)
}

class MiscEducationViewModelFactory(
    dependencies: MiscEducationUseCaseIO
) : ViewModelProviderFactory() {
    override val model: BaseViewModel = MiscEducationViewModel(dependencies)
}

class EducationViewModelFactory(
    dependencies: EducationUseCase
) : ViewModelProviderFactory() {
    override val model: BaseViewModel = EducationViewModel(dependencies)
}

class LanguageViewModelFactory(
    dependencies: LanguageUsesCasesIO
) : ViewModelProviderFactory() {
    override val model: BaseViewModel = LanguageViewModel(dependencies)
}

class QuestionaireViewModelFactory(
    dependencies: QuestionnaireUsesCasesIO
) : ViewModelProviderFactory() {
    override val model: BaseViewModel = QuestionnaireViewModel(dependencies)
}

class ProfileViewModelFactory(
    dependencies: ProfileUseCaseAndroid
) : ViewModelProviderFactory() {
    override val model: BaseViewModel = ProfileViewModel(dependencies)
}

object EmptyViewModel : BaseViewModel()