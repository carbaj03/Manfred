package com.acv.manfred.curriculum.presentation.operation

import arrow.Kind
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel


typealias UsesCasesIO = UseCase<ForIO>

interface UseCase<F> {
    fun RemoveQuestionnaireDto.removeView(): Kind<F, ResultK<List<QuestionnaireModel>>>
}