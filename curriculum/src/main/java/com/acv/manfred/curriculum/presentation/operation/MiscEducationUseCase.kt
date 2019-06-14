package com.acv.manfred.curriculum.presentation.operation

import arrow.Kind
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.AddMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.GetMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveMiscEducationDto
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel


typealias  MiscEducationUseCaseIO = MiscEducationUseCase<ForIO>

interface MiscEducationUseCase<F> {
    fun RemoveMiscEducationDto.removeView(): Kind<F, ResultK<List<MiscEducationModel>>>
    fun MiscEducationDto.saveView(): Kind<F, ResultK<List<MiscEducationModel>>>
    fun AddMiscEducationDto.addView(): Kind<F, ResultK<List<MiscEducationModel>>>
    fun GetMiscEducationDto.allView(): Kind<F, ResultK<List<MiscEducationModel>>>
}