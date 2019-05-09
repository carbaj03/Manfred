package com.acv.manfred.curriculum.presentation.form.component.common

interface Component<T : ComponentModel>  {
    fun renderType(model: T): Component<T>
}