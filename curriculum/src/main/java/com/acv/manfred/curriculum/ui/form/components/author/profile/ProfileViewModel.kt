package com.acv.manfred.curriculum.ui.form.components.author.profile

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.model.*
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentAction
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentAction.*
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.author.profile.StateProfile
import com.acv.manfred.curriculum.presentation.form.component.author.profile.StateProfile.*
import com.acv.manfred.curriculum.presentation.form.component.author.profile.StateProfile.Action
import com.acv.manfred.curriculum.presentation.operation.ProfileUseCaseAndroid
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.common.Error
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import com.acv.uikit.adapterModel.AndroidDiffResult
import com.acv.uikit.adapterModel.AndroidDiffer
import com.acv.uikit.adapterModel.AndroidUpdatable
import com.acv.uikit.adapterModel.AsyncDiffResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProfileViewModel(
    private val dependencies: ProfileUseCaseAndroid
) : BaseViewModel(), ProfileUseCaseAndroid by dependencies {

    //    val profile = MutableLiveData<List<ProfileModel>>()
    val profile = MutableLiveData<AsyncDiffResult<AndroidDiffer, AndroidUpdatable>>()
    val validation = MutableLiveData<ComponentValidation>()

    fun StateProfile.run(): Unit =
        when (this) {
            is Add -> add()
            is Load -> get()
            is Action -> componentAction.run()
        }

    private fun ProfileComponentAction.run(): Unit =
        when (this) {
            is Cancel -> {
            }
            is Remove -> remove(id)
            is Save -> save(response)
        }

    private fun add() {
        GlobalScope.async(Dispatchers.Main) {
            val a = AddProfileDto.addView()
//            a.fold(
//                ifLeft = { validation.value = Error(it.error) },
//                ifRight = {
//                    validation.value = Invalid
//                    profile.value = it
//                })
        }
    }


    private fun get() {
        GlobalScope.async(Dispatchers.Main) {
            val a = GetProfileDto.allView()
//            a.fold({ validation.value = Error(it.error) },
//                   {
//                       validation.value = Valid
//                       profile.value = it
//                   })
        }
    }

    private fun remove(id: GenerateId) {
        GlobalScope.async(Dispatchers.Main) {
            val a = RemoveProfileDto(id).removeView()
//            a.fold(
//                ifLeft = {
//                    when (it) {
//                        NotFoundError -> profile.value = AndroidDiffResult
//                        AuthenticationError, MapError,
//                        is UnknownServerError,
//                        is ApiError -> validation.value = Error(it.error)
//                    }
//                }
//                ,
//                ifRight = { profile.value = it }
//            )
        }
    }

    private fun save(education: ProfileComponentResponse) {
        GlobalScope.async(Dispatchers.Main) {
            education.toDto().saveView()
//            a.fold(
//                ifLeft = { validation.value = Error(it.error) },
//                ifRight = {
//                    validation.value = Valid
//                    profile.value = it
//                }
//            )
        }

    }
}

