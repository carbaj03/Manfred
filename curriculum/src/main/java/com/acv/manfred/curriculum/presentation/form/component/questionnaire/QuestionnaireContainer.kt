package com.acv.manfred.curriculum.presentation.form.component.questionnaire

import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.form.questionnaire.QuestionnaireViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentContainer
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireView

interface QuestionnaireContainer :
    ComponentContainer<QuestionnaireModel, QuestionnaireDefault, QuestionnaireComponentResponse, QuestionnaireView>,
    Observable<QuestionnaireViewModel>