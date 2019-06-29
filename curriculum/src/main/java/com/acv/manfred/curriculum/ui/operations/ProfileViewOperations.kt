package com.acv.manfred.curriculum.ui.operations

import com.acv.manfred.curriculum.data.gateway.request.RequestProfileOperations
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileModel
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ViewModel
import com.acv.manfred.curriculum.presentation.operation.ProfileUseCase
import com.acv.manfred.curriculum.ui.mapper.ProfileViewMapper

interface ProfileViewOperations : RequestProfileOperations, ProfileViewMapper, ProfileUseCase {

    override suspend fun ProfileDto.saveView(): ResultK<List<ProfileModel>> =
        save().toView()

    override suspend fun GetProfileDto.allView(): ResultK<List<ProfileModel>> =
        all().toView()

    override suspend fun AddProfileDto.addView(): ResultK<List<ProfileModel>> =
        add().toView()

    override suspend fun RemoveProfileDto.removeView(): ResultK<List<ProfileModel>> =
        remove().toView()

}