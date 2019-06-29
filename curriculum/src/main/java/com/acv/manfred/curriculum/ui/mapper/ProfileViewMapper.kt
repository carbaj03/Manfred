package com.acv.manfred.curriculum.ui.mapper

import arrow.core.Either
import arrow.core.flatMap
import com.acv.manfred.curriculum.data.gateway.datasource.catch
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.MapError
import com.acv.manfred.curriculum.domain.model.Profile
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileModel
import com.acv.manfred.curriculum.presentation.form.component.author.profile.toView

interface ProfileViewMapper {
    suspend fun ResultK<Profile>.toView(): ResultK<List<ProfileModel>> =
        flatMap { Either.catch { listOf(it.toView()) }.mapLeft { MapError } }
}