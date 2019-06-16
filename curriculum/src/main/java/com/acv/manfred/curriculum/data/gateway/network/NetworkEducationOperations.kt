package com.acv.manfred.curriculum.data.gateway.network

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.EducationEntity
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkEducationFetcher
import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.gategay.ResultK

interface NetworkEducationOperations<N> : NetworkEducationFetcher<N> {
    suspend fun EducationDto.persist(): ResultK<List<EducationEntity>> =
        saveEntity()

    suspend fun AddEducationDto.persist(): ResultK<List<EducationEntity>> =
        addEntity()

    suspend fun RemoveEducationDto.delete(): ResultK<List<EducationEntity>> =
        removeEntity()

    suspend fun GetEducationDto.request(): ResultK<List<EducationEntity>> =
        allEntity()
}