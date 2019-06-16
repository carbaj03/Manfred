package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.EducationEntity
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto

interface NetworkEducationFetcher<N> {
    suspend fun EducationDto.saveEntity(): ResultK<List<EducationEntity>>

    suspend fun AddEducationDto.addEntity(): ResultK<List<EducationEntity>>

    suspend fun RemoveEducationDto.removeEntity(): ResultK<List<EducationEntity>>

    suspend fun GetEducationDto.allEntity(): ResultK<List<EducationEntity>>
}