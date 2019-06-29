package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.core.Either
import arrow.core.flatMap
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.EducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toDomain
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Education
import com.acv.manfred.curriculum.domain.model.NotFoundError

interface DomainMapperEducatoin {
    suspend fun ResultK<List<EducationEntity>>.toDomainEducation(): ResultK<List<Education>> =
        flatMap { Either.catch {  it.map { it.toDomain() } }.mapLeft { NotFoundError } }
}