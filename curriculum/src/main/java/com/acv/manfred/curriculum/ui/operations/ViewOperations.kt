package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.RequestOperations
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireModel
import kotlin.coroutines.CoroutineContext

interface ViewOperations<F, N> : RequestOperations<F, N>, ViewMapper<F>, CvUseCase<F> {
    val main: CoroutineContext

    override fun QuestionnaireDto.saveView(): Kind<F, Result<List<QuestionnaireModel>>> =
        save().toViewQuestionnaire().continueOn(main)

    override fun GetQuestionnaireDto.allView(): Kind<F, Result<List<QuestionnaireModel>>> =
        all().toViewQuestionnaire().continueOn(main)

    override fun AddQuestionnaireDto.addView(): Kind<F, Result<List<QuestionnaireModel>>> =
        add().toViewQuestionnaire().continueOn(main)

    override fun RemoveQuestionnaireDto.removeView(): Kind<F, Result<List<QuestionnaireModel>>> =
        remove().toViewQuestionnaire().continueOn(main)

}