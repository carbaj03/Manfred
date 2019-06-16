package com.acv.manfred.curriculum.ui.mapper

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.toView

interface ProficiencyViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<Proficiency>>>.toViewP(): Kind<F, ResultK<List<ProficiencyModel>>> =
        flatMap { catch { it.map { list -> list.map { language -> language.toView() } } } }
}