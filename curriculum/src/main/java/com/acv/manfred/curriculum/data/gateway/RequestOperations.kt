package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.domain.model.RoleProfile
import kotlin.coroutines.CoroutineContext

interface RequestOperations<F, N> : Async<F>, NetworkOperations<F, N>, DomainMapper<F>, CvGateway<F> {
    val ctx: CoroutineContext

    override fun GetCvDto.get(): Kind<F, Result<Example>> =
        defer(ctx) { request().toDomainExample() }

    override fun RolesDto.get(): Kind<F, Result<List<RoleProfile>>> =
        defer(ctx) { request().toDomainRolePofile() }

    override fun ProficiencyDto.get(): Kind<F, Result<List<Proficiency>>> =
        defer(ctx) { request().toDomainProficiency() }

    override fun QuestionnaireDto.save(): Kind<F, Result<List<Questionnaire>>> =
        defer(ctx) { persist().toDomainQuestionnaire() }

    override fun GetQuestionnaireDto.all(): Kind<F, Result<List<Questionnaire>>> =
        defer(ctx) { request().toDomainQuestionnaire() }

    override fun RemoveQuestionnaireDto.remove(): Kind<F, Result<List<Questionnaire>>> =
        defer(ctx) { delete().toDomainQuestionnaire() }
}