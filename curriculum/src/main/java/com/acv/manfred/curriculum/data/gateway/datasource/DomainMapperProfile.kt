package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.core.Either
import arrow.core.flatMap
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProfileEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toDomain
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.MapError
import com.acv.manfred.curriculum.domain.model.Profile

interface DomainMapperProfile {
    suspend fun ResultK<ProfileEntity>.toDomainProfile(): ResultK<Profile> =
        flatMap { Either.catch { it.toDomain() }.mapLeft { MapError } }
}