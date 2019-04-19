package com.acv.uikit.dialog

import java.io.Serializable

sealed class DialogDateAction : Serializable {
    inline fun <R> evaluate(
        ifEmpty: () -> R,
        some: (String, FintonicDialogDateFragment.() -> Unit) -> R
    ): R =
        when (this) {
            is NoneDateAction -> ifEmpty()
            is SomeDateAction -> some(text, listener)
        }
}

object NoneDateAction : DialogDateAction()

class SomeDateAction(
    var text: String = "",
    var listener: FintonicDialogDateFragment.() -> Unit = {}
) : DialogDateAction()

interface DateDialog {
    fun SomeDateAction.listener(f: FintonicDialogDateFragment.() -> Unit) {
        listener = f
    }

    fun FintonicDialogDateFragment.accept(block: SomeDateAction.() -> Unit): SomeDateAction {
        val a = SomeDateAction().apply(block)
        accept = a
        return a
    }

    fun FintonicDialogDateFragment.cancel(block: SomeDateAction.() -> Unit): SomeDateAction {
        val a = SomeDateAction().apply(block)
        cancel = a
        return a
    }

    fun dateDialog(block: FintonicDialogDateFragment.() -> Unit): FintonicDialogDateFragment =
        FintonicDialogDateFragment().apply(block)
}
