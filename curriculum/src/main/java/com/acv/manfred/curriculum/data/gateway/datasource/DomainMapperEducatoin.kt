package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.core.Either
import arrow.core.EitherOf
import arrow.core.fix
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.EducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toDomain
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Education
import com.acv.manfred.curriculum.domain.model.NotFoundError

interface DomainMapperEducatoin {
    suspend fun ResultK<List<EducationEntity>>.toDomainEducation(): ResultK<List<Education>> =
        flatMap { Either.catch {  it.map { it.toDomain() } }.mapLeft { NotFoundError } }
}

suspend inline fun <A, B, C> EitherOf<A, B>.flatMap(f: suspend (B) -> Either<A, C>): Either<A, C> =
    fix().let {
        when (it) {
            is Either.Right -> f(it.b)
            is Either.Left -> it
        }
    }