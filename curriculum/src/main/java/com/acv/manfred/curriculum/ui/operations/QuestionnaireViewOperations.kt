package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.RequestQuestionnaireOperations
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.presentation.operation.QuestionnaireUseCase
import kotlin.coroutines.CoroutineContext

interface QuestionnaireViewOperations<F, N> : RequestQuestionnaireOperations<F, N>, QuestionnaireViewMapper<F>, QuestionnaireUseCase<F> {
    val main: CoroutineContext

    override fun QuestionnaireDto.saveView(): Kind<F, ResultK<List<QuestionnaireModel>>> =
        save().toViewQuestionnaire().continueOn(main)

    override fun GetQuestionnaireDto.allView(): Kind<F, ResultK<List<QuestionnaireModel>>> =
        all().toViewQuestionnaire().continueOn(main)

    override fun AddQuestionnaireDto.addView(): Kind<F, ResultK<List<QuestionnaireModel>>> =
        add().toViewQuestionnaire().continueOn(main)

    override fun RemoveQuestionnaireDto.removeView(): Kind<F, ResultK<List<QuestionnaireModel>>> =
        remove().toViewQuestionnaire().continueOn(main)

}