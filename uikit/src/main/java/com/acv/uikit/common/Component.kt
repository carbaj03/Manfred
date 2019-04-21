package com.acv.uikit.common

interface Component<M : Model> {
    fun render(model: M): Component<M>
}
