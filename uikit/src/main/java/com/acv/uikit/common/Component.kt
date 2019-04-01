package com.acv.uikit.common

interface Component<M : Model> {
    fun render(model: M): Component<M>

//    operator fun invoke(model: M.() -> Unit) : Component<M>
}
