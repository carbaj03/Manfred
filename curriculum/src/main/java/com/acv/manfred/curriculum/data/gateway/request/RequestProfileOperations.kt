package com.acv.manfred.curriculum.data.gateway.request

import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapperProfile
import com.acv.manfred.curriculum.data.gateway.network.NetworkProfileOperations
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ProfileGateway
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Profile

interface RequestProfileOperations : NetworkProfileOperations, DomainMapperProfile, ProfileGateway {

    override suspend fun ProfileDto.save(): ResultK<Profile> =
        persist().toDomainProfile()

    override suspend fun GetProfileDto.all(): ResultK<Profile> =
        request().toDomainProfile()

    override suspend fun AddProfileDto.add(): ResultK<Profile> =
        persist().toDomainProfile()

    override suspend fun RemoveProfileDto.remove(): ResultK<Profile> =
        delete().toDomainProfile()
}