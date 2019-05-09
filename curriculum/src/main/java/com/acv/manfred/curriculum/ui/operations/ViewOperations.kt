package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.RequestOperations
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.presentation.operation.QuestionnaireUseCase
import com.acv.manfred.curriculum.presentation.operation.UseCase
import kotlin.coroutines.CoroutineContext

interface ViewOperations<F, N> : RequestOperations<F, N>, ViewMapper<F>, UseCase<F> {
    val main: CoroutineContext

    override fun RemoveQuestionnaireDto.removeView(): Kind<F, ResultK<List<QuestionnaireModel>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        save().toViewQuestionnaire().continueOn(main)
    }
}