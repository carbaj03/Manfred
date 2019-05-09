package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.MiscEducationGateway
import com.acv.manfred.curriculum.domain.QuestionnaireGateway
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.domain.model.Questionnaire
import kotlin.coroutines.CoroutineContext

interface RequestMiscEducationOperations<F, N> : Async<F>, NetworkMiscEducationOperations<F, N>, DomainMapper<F>, MiscEducationGateway<F> {
    val ctx: CoroutineContext

    override fun MiscEducationDto.save(): Kind<F, ResultK<List<MiscEducation>>> =
        defer(ctx) { persist().toDomainMiscEducation() }

    override fun GetMiscEducationDto.all(): Kind<F, ResultK<List<MiscEducation>>> =
        defer(ctx) { request().toDomainMiscEducation() }

    override fun AddMiscEducationDto.add(): Kind<F, ResultK<List<MiscEducation>>> =
        defer(ctx) { persist().toDomainMiscEducation() }

    override fun RemoveMiscEducationDto.remove(): Kind<F, ResultK<List<MiscEducation>>> =
        defer(ctx) { delete().toDomainMiscEducation() }
}