package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import com.acv.manfred.curriculum.data.gateway.RequestMiscEducationOperations
import com.acv.manfred.curriculum.data.gateway.RequestQuestionnaireOperations
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.presentation.operation.MiscEducationUseCase
import com.acv.manfred.curriculum.presentation.operation.QuestionnaireUseCase
import kotlin.coroutines.CoroutineContext

interface MiscEducationViewOperations<F, N> : RequestMiscEducationOperations<F, N>, MiscEducationViewMapper<F>, MiscEducationUseCase<F> {
    val main: CoroutineContext

    override fun MiscEducationDto.saveView(): Kind<F, ResultK<List<MiscEducationModel>>> =
        save().toView().continueOn(main)

    override fun GetMiscEducationDto.allView(): Kind<F, ResultK<List<MiscEducationModel>>> =
        all().toView().continueOn(main)

    override fun AddMiscEducationDto.addView(): Kind<F, ResultK<List<MiscEducationModel>>> =
        add().toView().continueOn(main)

    override fun RemoveMiscEducationDto.removeView(): Kind<F, ResultK<List<MiscEducationModel>>> =
        remove().toView().continueOn(main)

}