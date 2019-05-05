package com.acv.manfred.curriculum.ui.form.components.common

interface Component<T : ComponentModel>  {
    fun renderType(model: T): Component<T>
}