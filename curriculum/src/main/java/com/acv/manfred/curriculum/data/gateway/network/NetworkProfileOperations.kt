package com.acv.manfred.curriculum.data.gateway.network

import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProfileEntity
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkProfileFetcher
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ResultK

interface NetworkProfileOperations : NetworkProfileFetcher {
    suspend fun ProfileDto.persist(): ResultK<ProfileEntity> =
        saveEntity()

    suspend fun AddProfileDto.persist(): ResultK<ProfileEntity> =
        addEntity()

    suspend fun RemoveProfileDto.delete(): ResultK<ProfileEntity> =
        removeEntity()

    suspend fun GetProfileDto.request(): ResultK<ProfileEntity> =
        allEntity()
}