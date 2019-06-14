package com.acv.manfred.curriculum.ui.form

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.dto.AddMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveMiscEducationDto
import com.acv.manfred.curriculum.domain.executeResult
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.NoId
import com.acv.manfred.curriculum.presentation.form.component.common.MiscEducationComponentAction
import com.acv.manfred.curriculum.presentation.form.component.common.MiscEducationComponentAction.*
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.StateMiscEducation
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.StateMiscEducation.*
import com.acv.manfred.curriculum.presentation.operation.MiscEducationUseCaseIO
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.common.Error
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.Valid

class MiscEducationViewModel(
    private val dependencies: MiscEducationUseCaseIO
) : BaseViewModel(), MiscEducationUseCaseIO by dependencies {
    val miscEducations = MutableLiveData<List<MiscEducationModel>>()
    val validation = MutableLiveData<ComponentValidation>()


    fun StateMiscEducation.run(): Unit =
        when (this) {
            is Add -> addMiscEducation()
            is Load -> getQuestionnaire()
            is Action -> componentAction.run()
        }

    private fun MiscEducationComponentAction.run(): Unit =
        when (this) {
            is Cancel -> { }
            is Remove -> remove(id)
            is Save -> save(item)
//            is MiscEducationChange -> TODO()
        }

    private fun addMiscEducation() {
        AddMiscEducationDto.addView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Invalid
                miscEducations.value = it
            })
    }

    private fun getQuestionnaire() {
        GetMiscEducationDto.allView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                miscEducations.value = it
            })
    }

    private fun remove(id: GenerateId) {
        RemoveMiscEducationDto(id).removeView().executeResult(
            error = { validation.value = Error(it.error) },
            success = { miscEducations.value = it }
        )
    }

    private fun save(miscEducation: MiscEducationComponentResponse) {
        miscEducation.toDto().saveView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                miscEducations.value = it
            }
        )
    }

}

