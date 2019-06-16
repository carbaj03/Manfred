package com.acv.manfred.curriculum.ui.mapper

import arrow.core.Either
import arrow.core.flatMap
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Education
import com.acv.manfred.curriculum.domain.model.MappError
import com.acv.manfred.curriculum.domain.model.NotFoundError
import com.acv.manfred.curriculum.presentation.form.component.education.EducationModel
import com.acv.manfred.curriculum.presentation.form.component.education.toView

interface EducationViewMapper  {

    suspend fun ResultK<List<Education>>.toView():  ResultK<List<EducationModel>> =
        flatMap { Either.catch { it.map { list -> list.toView() } }.mapLeft { MappError } }
}