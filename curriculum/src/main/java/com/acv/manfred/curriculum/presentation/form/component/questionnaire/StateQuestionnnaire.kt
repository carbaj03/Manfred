package com.acv.manfred.curriculum.presentation.form.component.questionnaire


sealed class StateQuestionnnaire {
    object Load : StateQuestionnnaire()
    object Add : StateQuestionnnaire()
    data class Action(val componentAction: QuestionnaireComponentAction) : StateQuestionnnaire()
}
