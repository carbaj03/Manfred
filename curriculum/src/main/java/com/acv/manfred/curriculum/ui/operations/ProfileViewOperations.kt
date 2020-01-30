package com.acv.manfred.curriculum.ui.operations

import arrow.core.Either
import com.acv.manfred.curriculum.data.gateway.datasource.flatMap
import com.acv.manfred.curriculum.data.gateway.request.RequestProfileOperations
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.ProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.MapError
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileModel
import com.acv.manfred.curriculum.presentation.operation.ProfileUseCase
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.mapper.ProfileDiffer
import com.acv.manfred.curriculum.ui.mapper.ProfileOldNew
import com.acv.manfred.curriculum.ui.mapper.ProfileUpdatable
import com.acv.manfred.curriculum.ui.mapper.ProfileViewMapper
import com.acv.uikit.adapterModel.AsyncDiffResult
import com.acv.uikit.adapterModel.Differ
import com.acv.uikit.adapterModel.ObserveComponent
import com.acv.uikit.adapterModel.Updatable

interface ProfileState {
    var oldState: List<ProfileModel>

    suspend fun ResultK<List<ProfileModel>>.change(): ResultK<ProfileOldNew> =
        flatMap {
            Either.catch {
                val temp: List<ProfileModel> = oldState
                oldState = it.map { it.copy() }
                ProfileOldNew(temp, it)
            }.mapLeft { MapError }
        }
}

interface ProfileViewOperations<A : AsyncDiffResult<B, C>, B : Differ, C : Updatable> :
    RequestProfileOperations,
    ProfileViewMapper,
    ProfileUseCase<A, B, C>,
    ProfileDiffer<B>,
    ProfileUpdatable<C>,
    AsyncDiffResult<B, C>,
    ProfileState
{
    val observeComponent: ObserveComponent<ProfileModel>
//    val validation : ComponentValidation

    override suspend fun ProfileDto.saveView(): Unit {
        val b = save().toView().change().diff()
        val c = save().toView().up(observeComponent)
        b.map { b1 -> c.map { c1 -> update(b1, c1) } }
    }

    override suspend fun GetProfileDto.allView(): Unit {
        val b = all().toView().change().diff()
        val c = all().toView().up(observeComponent)
        b.map { b1 -> c.map { c1 -> update(b1, c1) } }
    }

    override suspend fun AddProfileDto.addView(): Unit {
        val b = add().toView().change().diff()
        val c = add().toView().up(observeComponent)
        b.map { b1 -> c.map { c1 -> update(b1, c1) } }
    }

    override suspend fun RemoveProfileDto.removeView(): Unit {
        val b = remove().toView().change().diff()
        val c = remove().toView().up(observeComponent)
        b.map { b1 -> c.map { c1 -> update(b1, c1) } }
    }

}