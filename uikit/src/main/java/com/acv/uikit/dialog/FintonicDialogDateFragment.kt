package com.acv.uikit.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager.LayoutParams.MATCH_PARENT
import android.view.WindowManager.LayoutParams.WRAP_CONTENT
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import arrow.core.Option
import arrow.core.toOption
import com.acv.uikit.R
import com.acv.uikit.gone
import com.acv.uikit.onClick
import com.acv.uikit.visible
import kotlinx.android.synthetic.main.view_dialog_date_fragment.*
import java.util.*


class FintonicDialogDateFragment : DialogFragment() {
    companion object {
        operator fun invoke() = FintonicDialogDateFragment()
    }

    var title: String = ""
    val text : String get() = tieInput.text.toString()
    var accept: DialogDateAction = NoneDateAction
    var cancel: DialogDateAction = NoneDateAction
    var back: DialogFragment.() -> Unit = {}
    var cancelableOnOutsideTouch: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.view_dialog_date_fragment, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setLayout(MATCH_PARENT, WRAP_CONTENT)
    }

    private fun initView() {
        tvTitle.check(title.toOption())

        btAccept.setButton(accept)
        btCancel.setButton(cancel)

        isCancelable = cancelableOnOutsideTouch
    }



    private fun TextView.check(newText: Option<String>) = newText.fold(
        ifEmpty = { gone() },
        ifSome = {
            text = it
            visible()
        })

    private fun TextView.setButton(action: DialogDateAction) = action.evaluate(
        ifEmpty = { gone() },
        some = { s, action ->
            visible()
            text = s
            onClick { action(this@FintonicDialogDateFragment) }
        })

}