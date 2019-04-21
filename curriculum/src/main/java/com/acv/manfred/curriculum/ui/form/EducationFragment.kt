package com.acv.manfred.curriculum.ui.form

import android.app.DatePickerDialog
import android.content.Context
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.uikit.input.*
import kotlinx.android.synthetic.main.view_education.*
import java.util.*

class EducationFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.view_education

    override fun onCreate() {
        val cal = Calendar.getInstance()
        inputFrom.render(DateModel(cal) { cal.createDialog(inputFrom)(compatActivity) })
        inputUntil.render(DateModel(cal) { cal.createDialog(inputUntil)(compatActivity) })
    }

    fun Calendar.createDialog(input: Input): (Context) -> Unit = { ctx: Context ->
        DatePickerDialog(ctx, onDateSetListener(input)(ctx), get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun onDateSetListener(input: Input): (Context) -> DatePickerDialog.OnDateSetListener = { ctx: Context ->
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, monthOfYear)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    input.render(DateModel(this) { createDialog(input)(ctx) })
                }
            }
        }
}