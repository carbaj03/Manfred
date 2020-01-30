package com.acv.manfred.curriculum.presentation.operation

import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.uikit.adapterModel.*

typealias ProfileUseCaseAndroid = ProfileUseCase<AsyncDiffResult<AndroidDiffer, AndroidUpdatable>, AndroidDiffer, AndroidUpdatable>

interface ProfileUseCase<A : AsyncDiffResult<B, C>, B : Differ, C : Updatable> {
    suspend fun RemoveProfileDto.removeView(): Unit
    suspend fun ProfileDto.saveView(): Unit
    suspend fun AddProfileDto.addView(): Unit
    suspend fun GetProfileDto.allView(): Unit
}