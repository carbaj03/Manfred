package com.acv.manfred.curriculum.presentation.form.component.questionnaire

import com.acv.manfred.curriculum.presentation.form.component.common.QuestionnaireComponentAction


sealed class StateQuestionnnaire {
    object Load : StateQuestionnnaire()
    object Add : StateQuestionnnaire()
    data class Action(val componentAction: QuestionnaireComponentAction) : StateQuestionnnaire()
}
