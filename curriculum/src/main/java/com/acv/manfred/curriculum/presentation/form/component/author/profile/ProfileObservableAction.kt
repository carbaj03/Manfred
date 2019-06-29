package com.acv.manfred.curriculum.presentation.form.component.author.profile

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentAction


sealed class ProfileComponentAction : ComponentAction {
    data class Cancel(val id: GenerateId) : ProfileComponentAction()
    data class Remove(val id: GenerateId) : ProfileComponentAction()
    data class Save(val response: ProfileComponentResponse) : ProfileComponentAction()
}
