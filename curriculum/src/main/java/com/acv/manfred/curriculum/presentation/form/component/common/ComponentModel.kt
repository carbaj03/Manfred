package com.acv.manfred.curriculum.presentation.form.component.common

import com.acv.manfred.curriculum.domain.model.GenerateId

interface ComponentModel {
    val id: GenerateId
    val componentType: ComponentType
//    val position: Int
}