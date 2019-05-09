package com.acv.manfred.curriculum.presentation.form.component.common

import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireComponentResponse


//typealias QuestionnaireObservableAction = MutableLiveData<QuestionnaireComponentAction>

sealed class QuestionnaireComponentAction : ComponentAction
data class Cancel(val id: String) : QuestionnaireComponentAction()
data class Remove(val id: String) : QuestionnaireComponentAction()
data class Save(val item: QuestionnaireComponentResponse) : QuestionnaireComponentAction()