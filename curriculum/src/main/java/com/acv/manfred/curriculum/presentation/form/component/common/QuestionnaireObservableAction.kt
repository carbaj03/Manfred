package com.acv.manfred.curriculum.presentation.form.component.common

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireComponentResponse


//typealias QuestionnaireObservableAction = MutableLiveData<QuestionnaireComponentAction>

sealed class QuestionnaireComponentAction : ComponentAction
data class Cancel(val id: GenerateId) : QuestionnaireComponentAction()
data class Remove(val id: GenerateId) : QuestionnaireComponentAction()
data class Save(val item: QuestionnaireComponentResponse) : QuestionnaireComponentAction()