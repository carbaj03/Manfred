package com.acv.manfred.curriculum.presentation.form.component.common

sealed class ComponentState
object NotModified : ComponentState()
sealed class Modified : ComponentState()
object Incompleted : Modified()
object Completed : Modified()