package com.acv.manfred.curriculum.ui.form.components.author.profile

import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.fx
import arrow.fx.extensions.io.unsafeRun.runBlocking
import arrow.fx.extensions.io.unsafeRun.runNonBlocking
import arrow.fx.typeclasses.ConcurrentSyntax
import arrow.unsafe
import com.acv.manfred.curriculum.domain.dto.AddProfileDto
import com.acv.manfred.curriculum.domain.dto.GetProfileDto
import com.acv.manfred.curriculum.domain.dto.RemoveProfileDto
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentAction
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentAction.*
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.author.profile.StateProfile
import com.acv.manfred.curriculum.presentation.form.component.author.profile.StateProfile.*
import com.acv.manfred.curriculum.presentation.operation.ProfileUseCaseAndroid
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProfileViewModel(
    private val dependencies: ProfileUseCaseAndroid
) : BaseViewModel(), ProfileUseCaseAndroid by dependencies {

    //    val profile = MutableLiveData<List<ProfileModel>>()
//    val profile = MutableLiveData<AsyncDiffResult<AndroidDiffer, AndroidUpdatable>>()
//    val validation = MutableLiveData<ComponentValidation>()

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

    fun launch(f: suspend ConcurrentSyntax<ForIO>.() -> Unit) =
        unsafe { runNonBlocking({ IO.fx { f() } } ){}}

    private fun add() {
        launch {
            continueOn(Dispatchers.Main)
            !effect { AddProfileDto.addView() } }
//            GlobalScope.async(Dispatchers.Main) {
//                val a = AddProfileDto.addView()
//            a.fold(
//                ifLeft = { validation.value = Error(it.error) },
//                ifRight = {
//                    validation.value = Invalid
//                    profile.value = it
//                })
//            }
//        }
    }

    private fun get() {
        launch {
            continueOn(Dispatchers.Main)
            !effect { GetProfileDto.allView() } }
//        GlobalScope.async(Dispatchers.Main) {
//            val a = GetProfileDto.allView()
//            a.fold({ validation.value = Error(it.error) },
//                   {
//                       validation.value = Valid
//                       profile.value = it
//                   })
//        }
    }

    private fun remove(id: GenerateId) {
        launch {
            continueOn(Dispatchers.Main)
            !effect { RemoveProfileDto(id).removeView() } }
//        GlobalScope.async(Dispatchers.Main) {
//            val a = RemoveProfileDto(id).removeView()
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
//        }
    }

    private fun save(education: ProfileComponentResponse) {
        launch {
            continueOn(Dispatchers.Main)
            !effect { education.toDto().saveView() } }
//        GlobalScope.async(Dispatchers.Main) {
//            education.toDto().saveView()
//            a.fold(
//                ifLeft = { validation.value = Error(it.error) },
//                ifRight = {
//                    validation.value = Valid
//                    profile.value = it
//                }
//            )
//        }

    }
}

