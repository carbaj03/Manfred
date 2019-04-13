package com.acv.manfred.curriculum.ui.form

import androidx.lifecycle.MutableLiveData
import arrow.effects.typeclasses.Disposable
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.example.RoleProfile
import com.acv.manfred.curriculum.data.gateway.datasource.Role
import com.acv.manfred.curriculum.domain.GatewayIO
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.RolesDto
import com.acv.manfred.curriculum.domain.executeResult
import com.acv.manfred.curriculum.ui.common.arch.BaseViewModel

class FormViewModel(
    private val dependencies: GatewayIO
) : BaseViewModel(), GatewayIO by dependencies {

    val cv by lazy { MutableLiveData<Example>() }
    val roles by lazy { MutableLiveData<List<RoleProfile>>() }


    fun getCv() {
        GetCvDto("", "").get().executeResult { cv.value = it }
    }

    fun getRoles() {
        RolesDto("", "").get().executeResult { roles.value = it }
    }


}
