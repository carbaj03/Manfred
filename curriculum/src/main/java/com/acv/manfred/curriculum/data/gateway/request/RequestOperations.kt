package com.acv.manfred.curriculum.data.gateway.request

import arrow.Kind
import arrow.fx.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.network.NetworkOperations
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.gategay.CvGateway
import com.acv.manfred.curriculum.domain.gategay.ResultK
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