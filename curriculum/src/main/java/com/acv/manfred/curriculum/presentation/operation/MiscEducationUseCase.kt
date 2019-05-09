package com.acv.manfred.curriculum.presentation.operation

import arrow.Kind
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel


typealias  MiscEducationUseCaseIO = MiscEducationUseCase<ForIO>

interface MiscEducationUseCase<F> {
    fun RemoveMiscEducationDto.removeView(): Kind<F, ResultK<List<MiscEducationModel>>>
    fun MiscEducationDto.saveView(): Kind<F, ResultK<List<MiscEducationModel>>>
    fun AddMiscEducationDto.addView(): Kind<F, ResultK<List<MiscEducationModel>>>
    fun GetMiscEducationDto.allView(): Kind<F, ResultK<List<MiscEducationModel>>>
}