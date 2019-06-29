package com.acv.manfred.curriculum.presentation.operation

import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileModel
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ViewModel

interface ProfileUseCase {
    suspend fun RemoveProfileDto.removeView(): ResultK<List<ProfileModel>>
    suspend fun ProfileDto.saveView():  ResultK<List<ProfileModel>>
    suspend fun AddProfileDto.addView():  ResultK<List<ProfileModel>>
    suspend fun GetProfileDto.allView():  ResultK<List<ProfileModel>>
}