package com.acv.manfred.curriculum.ui.form.components.education

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.education.EducationComponentAction
import com.acv.manfred.curriculum.presentation.form.component.education.EducationComponentAction.*
import com.acv.manfred.curriculum.presentation.form.component.education.EducationComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.education.EducationModel
import com.acv.manfred.curriculum.presentation.form.component.education.StateEducation
import com.acv.manfred.curriculum.presentation.form.component.education.StateEducation.*
import com.acv.manfred.curriculum.presentation.operation.EducationUseCase
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.common.Error
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EducationViewModel(
    private val dependencies: EducationUseCase
) : BaseViewModel(), EducationUseCase by dependencies {

    val educations = MutableLiveData<List<EducationModel>>()
    val validation = MutableLiveData<ComponentValidation>()


    fun StateEducation.run(): Unit =
        when (this) {
            is Add -> addEducation()
            is Load -> getQuestionnaire()
            is Action -> componentAction.run()
        }

    private fun EducationComponentAction.run(): Unit =
        when (this) {
            is Cancel -> {
            }
            is Remove -> remove(id)
            is Save -> save(item)
        }

    private fun addEducation() {
        GlobalScope.async(Dispatchers.Main) {
            val a = AddEducationDto.addView()
            a.fold(
                ifLeft = { validation.value = Error(it.error) },
                ifRight = {
                    validation.value = Invalid
                    educations.value = it
                })
        }
    }


    private fun getQuestionnaire() {
        GlobalScope.async(Dispatchers.Main) {
            val a = GetEducationDto.allView()
            a.fold({ validation.value = Error(it.error) },
                   {
                       validation.value = Valid
                       educations.value = it
                   })
        }
    }

    private fun remove(id: GenerateId) {
        GlobalScope.async(Dispatchers.Main) {
            val a = RemoveEducationDto(id).removeView()
            a.fold(
                ifLeft = { validation.value = Error(it.error) },
                ifRight = { educations.value = it }
            )
        }
    }

    private fun save(education: EducationComponentResponse) {
        GlobalScope.async(Dispatchers.Main) {
            val a = education.toDto().saveView()
            a.fold(
                ifLeft = { validation.value = Error(it.error) },
                ifRight = {
                    validation.value = Valid
                    educations.value = it
                }
            )
        }

    }
}

