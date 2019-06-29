package com.acv.manfred.curriculum.data.gateway.network.fetcher

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProfileEntity
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ResultK

interface NetworkProfileFetcher {
    suspend fun ProfileDto.saveEntity(): ResultK<ProfileEntity>

    suspend fun AddProfileDto.addEntity(): ResultK<ProfileEntity>

    suspend fun RemoveProfileDto.removeEntity(): ResultK<ProfileEntity>

    suspend fun GetProfileDto.allEntity(): ResultK<ProfileEntity>
}