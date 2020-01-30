package com.acv.manfred.curriculum.presentation.operation

import arrow.Kind
import arrow.fx.ForIO
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel


typealias QuestionnaireUsesCasesIO = QuestionnaireUseCase<ForIO>

interface QuestionnaireUseCase<F> {
    fun RemoveQuestionnaireDto.removeView() : Kind<F, ResultK<List<QuestionnaireModel>>>
    fun QuestionnaireDto.saveView() : Kind<F, ResultK<List<QuestionnaireModel>>>
    fun AddQuestionnaireDto.addView() : Kind<F, ResultK<List<QuestionnaireModel>>>
    fun GetQuestionnaireDto.allView() : Kind<F, ResultK<List<QuestionnaireModel>>>
}