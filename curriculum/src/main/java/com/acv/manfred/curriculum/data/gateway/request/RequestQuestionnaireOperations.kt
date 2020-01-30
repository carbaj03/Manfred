package com.acv.manfred.curriculum.data.gateway.request

import arrow.Kind
import arrow.fx.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.network.NetworkQuestionnaireOperations
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.gategay.QuestionnaireGateway
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto
import com.acv.manfred.curriculum.domain.model.Questionnaire
import kotlin.coroutines.CoroutineContext

interface RequestQuestionnaireOperations<F, N> : Async<F>, NetworkQuestionnaireOperations<F, N>, DomainMapper<F>, QuestionnaireGateway<F> {
    val ctx: CoroutineContext

    override fun QuestionnaireDto.save(): Kind<F, ResultK<List<Questionnaire>>> =
        defer(ctx) { persist().toDomainQuestionnaire() }

    override fun GetQuestionnaireDto.all(): Kind<F, ResultK<List<Questionnaire>>> =
        defer(ctx) { request().toDomainQuestionnaire() }

    override fun AddQuestionnaireDto.add(): Kind<F, ResultK<List<Questionnaire>>> =
        defer(ctx) { persist().toDomainQuestionnaire() }

    override fun RemoveQuestionnaireDto.remove(): Kind<F, ResultK<List<Questionnaire>>> =
        defer(ctx) { delete().toDomainQuestionnaire() }
}