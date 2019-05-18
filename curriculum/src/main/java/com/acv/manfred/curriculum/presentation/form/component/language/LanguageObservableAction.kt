package com.acv.manfred.curriculum.presentation.form.component.language

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentAction


//typealias QuestionnaireObservableAction = MutableLiveData<QuestionnaireComponentAction>

sealed class LanguageComponentAction : ComponentAction
data class Cancel(val id: GenerateId) : LanguageComponentAction()
data class Remove(val id: GenerateId) : LanguageComponentAction()
data class Save(val item: LanguageComponentResponse) : LanguageComponentAction()