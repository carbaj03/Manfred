package com.acv.manfred.curriculum.ui.form.languaje

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.dto.AddLanguageDto
import com.acv.manfred.curriculum.domain.dto.GetLanguageDto
import com.acv.manfred.curriculum.domain.dto.RemoveLanguageDto
import com.acv.manfred.curriculum.domain.executeResult
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.language.*
import com.acv.manfred.curriculum.presentation.form.component.language.StateLanguage.*
import com.acv.manfred.curriculum.presentation.operation.LanguageUsesCasesIO
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.common.Error
import com.acv.manfred.curriculum.ui.form.components.common.Valid

class LanguageViewModel(
    private val dependencies: LanguageUsesCasesIO
) : BaseViewModel(), LanguageUsesCasesIO by dependencies {

    val languages = MutableLiveData<List<LanguageModel>>()
    val validation = MutableLiveData<ComponentValidation>()

    fun StateLanguage.run(): Unit =
        when (this) {
            is Add -> addLanguage()
            is Load -> getLanguage()
            is Action -> componentAction.run()
        }

    private fun LanguageComponentAction.run(): Unit =
        when (this) {
            is Cancel -> { }
            is Remove -> remove(id)
            is Save -> item.save()
//            is LanguageChange -> TODO()
//            is ProficiencyChange -> TODO()
        }

    private fun addLanguage() {
        AddLanguageDto.addView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                languages.value = it
            })
    }

    private fun getLanguage() {
        GetLanguageDto.allView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                languages.value = it
            })
    }

    private fun remove(id: GenerateId) {
        RemoveLanguageDto(id).removeView().executeResult(
            error = { validation.value = Error(it.error) },
            success = { languages.value = it }
        )
    }

    private fun LanguageComponentResponse.save() {
        toDto().saveView().executeResult(
            error = { validation.value = Error(it.error) },
            success = {
                validation.value = Valid
                languages.value = it
            }
        )
    }


}
