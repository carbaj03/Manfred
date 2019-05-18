package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.CvGateway
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.dto.GetCvDto
import com.acv.manfred.curriculum.domain.dto.RolesDto
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.RoleProfile
import kotlin.coroutines.CoroutineContext

interface RequestOperations<F, N> : Async<F>, NetworkOperations<F, N>, DomainMapper<F>, CvGateway<F> {
    val ctx: CoroutineContext

    override fun GetCvDto.get(): Kind<F, ResultK<Example>> =
        defer(ctx) { request().toDomainExample() }

    override fun RolesDto.get(): Kind<F, ResultK<List<RoleProfile>>> =
        defer(ctx) { request().toDomainRolePofile() }
}