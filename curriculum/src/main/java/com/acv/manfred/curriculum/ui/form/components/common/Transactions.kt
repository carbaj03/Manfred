package com.acv.manfred.curriculum.ui.form.components.common

import com.acv.manfred.curriculum.ui.form.QuestionnaireModel


sealed class ComponentState
data class Error(val msg: String) : ComponentState()
object Valid : ComponentState()
object Invalid : ComponentState()


sealed class ComponentAction
data class Remove(val id: String) : ComponentAction()
data class Save(val items: List<QuestionnaireModel>) : ComponentAction()