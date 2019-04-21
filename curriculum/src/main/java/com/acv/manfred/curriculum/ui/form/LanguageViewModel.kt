package com.acv.manfred.curriculum.ui.form

import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.domain.GatewayIO
import com.acv.manfred.curriculum.domain.ProficiencyDto
import com.acv.manfred.curriculum.domain.executeResult
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel

class LanguageViewModel(
    private val dependencies: GatewayIO
) : BaseViewModel(), GatewayIO by dependencies {

    val proficiencies by lazy { MutableLiveData<List<Proficiency>>() }

    fun getProficiencies() {
        ProficiencyDto("", "").get().executeResult { proficiencies.value = it }
    }


}
