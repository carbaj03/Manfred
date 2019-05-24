package com.acv.manfred.curriculum.presentation.form.component.common

import android.view.View
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.*
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import com.acv.uikit.common.textWatcher
import com.acv.uikit.input.Input
import com.acv.uikit.invisible
import com.acv.uikit.onClick
import com.acv.uikit.visible
import com.google.android.material.button.MaterialButton

interface WithResponse<A : ComponentResponse, B : ByDefault> {
    fun B.createResponse(): A
}

interface
Component<A : ComponentModel, B : ByDefault, C : ComponentResponse> : Validable , WithResponse<C, B>{

    fun renderType(model: A): Component<A, B, C> {
        state.value = if (model.componentType is Persisted) Valid else Invalid
        model.renderFields()
        model.renderType()
        model.listener()
        return this
    }

    fun A.renderFields()

    fun A.listener()

    fun A.renderType() =
        when (componentType) {
            is Persisted -> persistedState()
            is New -> newState()
        }

    fun A.newState() =
        when (componentType.componentState) {
            Incompleted -> newIncompled()
            Completed -> newCompleted()
            NotModified -> newNotModified()
        }

    fun A.newIncompled()
    fun A.newCompleted()
    fun A.newNotModified()

    fun A.persistedState() =
        when (componentType.componentState) {
            Incompleted -> persistedIncompled()
            Completed -> persistedCompleted()
            NotModified -> persistedNotModified()
        }

    fun A.persistedIncompled()
    fun A.persistedCompleted()
    fun A.persistedNotModified()

    fun MaterialButton.enabled(f: () -> Unit) {
        visible()
        onClick { f() }
    }

    fun invisible(vararg views: View): Unit =
        views.forEach { it.invisible() }

    fun Input.listener(toWatch: (String) -> Unit): Unit =
        watch(textWatcher { toWatch(it.toString()) })

    fun valid(byDefault: B): Unit

    fun invalid(): Unit


    val isModified: ComponentState

    val isCompleted
        get() = isModified is Completed

    val isIncompleted
        get() = isModified is Incompleted

    val Input.isValid: Boolean
        get() = value.isNotBlank()

    val Input.isInvalid: Boolean
        get() = value.isBlank()

    fun A.createByDefault(): B


    fun MaterialButton.enableRemove(model: B): Unit

    fun MaterialButton.enableSave(model: B): Unit

    fun MaterialButton.enableCancel(byDefault: B): Unit
}