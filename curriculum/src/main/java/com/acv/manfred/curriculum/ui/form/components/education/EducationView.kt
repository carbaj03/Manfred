package com.acv.manfred.curriculum.ui.form.components.education

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.presentation.form.component.common.Actionable
import com.acv.manfred.curriculum.presentation.form.component.common.Completed
import com.acv.manfred.curriculum.presentation.form.component.common.Incompleted
import com.acv.manfred.curriculum.presentation.form.component.common.Modified
import com.acv.manfred.curriculum.presentation.form.component.education.*
import com.acv.manfred.curriculum.presentation.form.component.education.EducationComponentAction.Remove
import com.acv.manfred.curriculum.presentation.form.component.education.EducationComponentAction.Save
import com.acv.manfred.curriculum.ui.form.components.DateFormatter
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.uikit.input.DateModel
import com.acv.uikit.input.Input
import com.acv.uikit.invisible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_actions.view.*
import kotlinx.android.synthetic.main.component_education.view.*
import kotlinx.android.synthetic.main.component_education.view.info
import java.util.*

class EducationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr),
    Actionable<EducationComponentAction>,
    EducationComponent,
    DateFormatter {

    init {
        inflate(context, R.layout.component_education, this)
        orientation = VERTICAL
    }

    private var studyC
        get() = inputStudy.value
        set(value) {
            inputStudy.value = value
        }

    private var untilC
        get() = inputUntil.value
        set(value) {
            inputUntil.value = value
        }

    private var fromC
        get() = inputFrom.value
        set(value) {
            inputFrom.value = value
        }

    private var institutionC
        get() = inputInstitution.value
        set(value) {
            inputInstitution.value = value
        }

    override val state: ObservableValidation = MutableLiveData()

    override val actions: MutableLiveData<EducationComponentAction> = MutableLiveData()

    override val isModified: Modified
        get() = when {
            inputInstitution.isValid && inputStudy.isValid && inputUntil.isValid && inputFrom.isValid -> Completed
            else -> Incompleted
        }

    override fun EducationModel.createByDefault(): EducationDefault =
        EducationDefault(id = id, institution = institution, study = study, from = from, until = until)

    override fun EducationModel.renderFields() {
        institutionC = institution
        studyC = study
        fromC = from
        untilC = until
        info.text = id.id

        val cal = Calendar.getInstance()
        inputFrom.render(DateModel(from) { from.createCalendarFromFormatter().fold({ cal.createDialog(inputFrom)(context) }, { it.createDialog(inputFrom)(context) }) })
        inputUntil.render(DateModel(until) { until.createCalendarFromFormatter().fold({ cal.createDialog(inputUntil)(context) }, { it.createDialog(inputUntil)(context) }) })
    }

    override fun EducationModel.listener() {
        inputInstitution.listener { if (it == institution || isIncompleted) invalid() else valid(createByDefault()) }
        inputStudy.listener { if (it == study || isIncompleted) invalid() else valid(createByDefault()) }
        inputFrom.listener { if (it == from || isIncompleted) invalid() else valid(createByDefault()) }
        inputUntil.listener { if (it == until || isIncompleted) invalid() else valid(createByDefault()) }
    }

    override fun valid(byDefault: EducationDefault) {
        btnCancel.enableCancel(byDefault)
        btnSave.enableSave(byDefault)
    }

    override fun invalid() {
        invisible(btnCancel, btnSave)
    }

    override fun EducationModel.newIncompled() {
        btnCancel.enableCancel(createByDefault())
        invisible(btnRemove, btnSave)
    }

    override fun EducationModel.newCompleted() {
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnRemove.invisible()
    }

    override fun EducationModel.newNotModified() {
        invisible(btnRemove, btnSave, btnCancel)
    }

    override fun EducationModel.persistedIncompled() {
        btnRemove.enableRemove(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnSave.invisible()
    }

    override fun EducationModel.persistedCompleted() {
        btnRemove.enableRemove(createByDefault())
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
    }

    override fun EducationModel.persistedNotModified() {
        btnRemove.enableRemove(createByDefault())
        invisible(btnCancel, btnSave)
    }

    override fun MaterialButton.enableRemove(model: EducationDefault) =
        enabled { actions.value = Remove(model.id) }

    override fun MaterialButton.enableSave(model: EducationDefault) =
        enabled { actions.value = Save(model.createResponse()) }

    override fun MaterialButton.enableCancel(byDefault: EducationDefault) =
        enabled {
            institutionC = byDefault.institution
            studyC = byDefault.study
            fromC = byDefault.from
            untilC = byDefault.until
            invisible()
        }

    override fun EducationDefault.createResponse(): EducationComponentResponse =
        EducationComponentResponse(
            id = id,
            institution = institutionC,
            study = studyC,
            from = fromC,
            until = untilC
        )

    fun Calendar.createDialog(input: Input): (Context) -> Unit = { ctx: Context ->
        DatePickerDialog(ctx, onDateSetListener(input)(ctx), get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun onDateSetListener(input: Input): (Context) -> DatePickerDialog.OnDateSetListener = { ctx: Context ->
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, monthOfYear)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                input.render(DateModel(getDisplayFormat()) { createDialog(input)(ctx) })
            }
        }
    }
}