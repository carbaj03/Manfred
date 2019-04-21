package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.model.BaseError
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.domain.model.RoleProfile

typealias GatewayIO = CvGateway<ForIO>

typealias Result<A> = Either<BaseError, A>

interface CvGateway<F> {
    fun GetCvDto.get(): Kind<F, Result<Example>>
    fun RolesDto.get(): Kind<F, Result<List<RoleProfile>>>
    fun ProficiencyDto.get(): Kind<F, Result<List<Proficiency>>>
}

