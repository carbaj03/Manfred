package com.acv.manfred.curriculum.ui.form

import android.app.DatePickerDialog
import arrow.core.Eval
import arrow.core.some
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.uikit.input.InputDate
import com.acv.uikit.input.InputModel
import kotlinx.android.synthetic.main.view_education.*
import java.util.*

class EducationFragment : BaseFragment() {
    val from = Calendar.getInstance()
    val until = Calendar.getInstance()

    override fun getLayout(): Int = R.layout.view_education

    override fun onCreate() {
        inputFrom.render(InputModel(InputDate, "sadf", Eval.always { clickFrom() }.some()))
        inputUntil.render(InputModel(InputDate, "sadf", Eval.always { clickUntil() }.some()))
    }

    fun clickFrom() {

        val year = from.get(Calendar.YEAR)
        val month = from.get(Calendar.MONTH)
        val day = from.get(Calendar.DAY_OF_MONTH)

        val dpd =
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                from.set(Calendar.YEAR, year)
                from.set(Calendar.MONTH, monthOfYear)
                from.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                inputFrom.render(InputModel(InputDate, """$dayOfMonth - ${monthOfYear + 1} - $year""", Eval.always { clickFrom() }.some()))
            }, year, month, day)
        dpd.show()
    }

    fun clickUntil() {

        val year = until.get(Calendar.YEAR)
        val month = until.get(Calendar.MONTH)
        val day = until.get(Calendar.DAY_OF_MONTH)

        val dpd =
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                until.set(Calendar.YEAR, year)
                until.set(Calendar.MONTH, monthOfYear)
                until.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                inputUntil.render(InputModel(InputDate, """$dayOfMonth - ${monthOfYear + 1} - $year""", Eval.always { clickUntil() }.some()))
            }, year, month, day)
        dpd.show()
    }


}


