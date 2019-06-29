package com.acv.manfred.curriculum.presentation.form.component.education

import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentAction


sealed class EducationComponentAction : ComponentAction {
    data class Cancel(val id: GenerateId) : EducationComponentAction()
    data class Remove(val id: GenerateId) : EducationComponentAction()
    data class Save(val item: EducationComponentResponse) : EducationComponentAction()
}
