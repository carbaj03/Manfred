package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.domain.model.RoleProfile
import kotlin.coroutines.CoroutineContext

interface RequestOperations<F, N> : Async<F>, NetworkOperations<F, N>, DomainMapper<F>, CvGateway<F> {
    val ctx: CoroutineContext
    val main: CoroutineContext

    override fun GetCvDto.get(): Kind<F, Result<Example>> =
        defer(ctx) {
            request().toDomainExample()
        }

    override fun RolesDto.get(): Kind<F, Result<List<RoleProfile>>> =
        defer(ctx) {
            request().toDomainRolePofile()
        }.continueOn(main)

    override fun ProficiencyDto.get(): Kind<F, Result<List<Proficiency>>> =
        defer(ctx) {
            request().toDomainProficiency()
        }.continueOn(main)
}