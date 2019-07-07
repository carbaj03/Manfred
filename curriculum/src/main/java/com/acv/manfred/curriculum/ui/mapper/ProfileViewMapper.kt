package com.acv.manfred.curriculum.ui.mapper

import arrow.core.Either
import arrow.core.flatMap
import com.acv.manfred.curriculum.data.gateway.datasource.catch
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.MapError
import com.acv.manfred.curriculum.domain.model.Profile
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileModel
import com.acv.manfred.curriculum.presentation.form.component.author.profile.toView
import com.acv.uikit.adapterModel.*

interface ProfileViewMapper {
    suspend fun ResultK<Profile>.toView(): ResultK<List<ProfileModel>> =
        flatMap { Either.catch { listOf(it.toView()) }.mapLeft { MapError } }
}

interface ProfileDiffer<A : Differ> : Diff<A> {

    suspend fun ResultK<ProfileOldNew>.diff(): ResultK<A> =
        flatMap { Either.catch { notify(it.oldState, it.newState) { a, b -> a.id.id == b.id.id } }.mapLeft { MapError } }
}

data class ProfileOldNew(val oldState: List<ProfileModel>, val newState : List<ProfileModel>)


interface ProfileUpdatable<A : Updatable> : Up<A> {

    suspend fun ResultK<List<ProfileModel>>.up(oldState: ObserveComponent<ProfileModel>): ResultK<A> =
        flatMap { Either.catch { notify(oldState, it)  }.mapLeft { MapError } }

}