package com.acv.manfred.curriculum.ui.mapper

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.toView

interface MiscEducationViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<MiscEducation>>>.toView(): Kind<F, ResultK<List<MiscEducationModel>>> =
        flatMap { catch { it.map { list -> list.map { miscEducation -> miscEducation.toView() } } } }
}