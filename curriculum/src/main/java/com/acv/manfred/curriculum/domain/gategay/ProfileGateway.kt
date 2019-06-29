package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.model.Profile

interface ProfileGateway {
    suspend fun ProfileDto.save(): ResultK<Profile>
    suspend fun AddProfileDto.add():  ResultK<Profile>
    suspend fun RemoveProfileDto.remove():  ResultK<Profile>
    suspend fun GetProfileDto.all():  ResultK<Profile>
}