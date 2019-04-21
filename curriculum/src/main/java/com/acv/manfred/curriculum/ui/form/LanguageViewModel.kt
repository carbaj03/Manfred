package com.acv.manfred.curriculum.ui.form

import androidx.lifecycle.MutableLiveData
import arrow.effects.typeclasses.Disposable
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.example.Proficiency
import com.acv.manfred.curriculum.data.example.RoleProfile
import com.acv.manfred.curriculum.data.gateway.datasource.Role
import com.acv.manfred.curriculum.domain.*
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel

class LanguageViewModel(
    private val dependencies: GatewayIO
) : BaseViewModel(), GatewayIO by dependencies {

    val proficiencies by lazy { MutableLiveData<List<Proficiency>>() }

    fun getProficiencies() {
        ProficiencyDto("", "").get().executeResult { proficiencies.value = it }
    }


}
