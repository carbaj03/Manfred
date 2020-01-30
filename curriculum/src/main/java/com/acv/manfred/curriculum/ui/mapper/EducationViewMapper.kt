package com.acv.manfred.curriculum.ui.mapper

import arrow.core.Either
import com.acv.manfred.curriculum.data.gateway.datasource.flatMap
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Education
import com.acv.manfred.curriculum.domain.model.MapError
import com.acv.manfred.curriculum.presentation.form.component.education.EducationModel
import com.acv.manfred.curriculum.presentation.form.component.education.toView

interface EducationViewMapper  {
    suspend fun ResultK<List<Education>>.toView():  ResultK<List<EducationModel>> =
        flatMap { Either.catch { it.map { list -> list.toView() } }.mapLeft { MapError } }
}