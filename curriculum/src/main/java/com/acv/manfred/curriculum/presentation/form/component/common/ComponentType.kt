package com.acv.manfred.curriculum.presentation.form.component.common

sealed class ComponentType(open val componentState: ComponentState)
data class Persisted(override val componentState: ComponentState) : ComponentType(componentState)
data class New(override val componentState: ComponentState) : ComponentType(componentState)