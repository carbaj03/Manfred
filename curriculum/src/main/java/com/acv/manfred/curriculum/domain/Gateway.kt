package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.example.RoleProfile
import com.acv.manfred.curriculum.data.gateway.datasource.Role
import com.acv.manfred.curriculum.domain.model.BaseError

typealias GatewayIO = CvGateway<ForIO>

typealias Result<A> = Either<BaseError, A>

interface CvGateway<F> {
    fun GetCvDto.get(): Kind<F, Result<Example>>
    fun RolesDto.get(): Kind<F, Result<List<RoleProfile>>>
}

