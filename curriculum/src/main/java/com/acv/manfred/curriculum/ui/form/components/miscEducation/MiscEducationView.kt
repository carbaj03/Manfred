package com.acv.manfred.curriculum.ui.form.components.miscEducation

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentAction
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentAction.Remove
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentAction.Save
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponent
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationDefault
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.uikit.invisible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_actions.view.*
import kotlinx.android.synthetic.main.component_misc_education.view.*

class MiscEducationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr),
    Actionable<MiscEducationComponentAction>,
    MiscEducationComponent {

    init {
        inflate(context, R.layout.component_misc_education, this)
        orientation = VERTICAL
    }

    override val state: ObservableValidation = MutableLiveData()

    override val actions: MutableLiveData<MiscEducationComponentAction> = MutableLiveData()

    override val isModified: Modified
        get() = when {
            inputMiscEducation.isValid -> Completed
            else -> Incompleted
        }

    private var miscEducationC: String
        get() = inputMiscEducation.value
        set(value) {
            inputMiscEducation.value = value
        }

    override fun MiscEducationModel.createByDefault(): MiscEducationDefault =
        MiscEducationDefault(id = id, miscEducation = miscellaneous)

    override fun MiscEducationModel.renderFields() {
        miscEducationC = miscellaneous
        info.text = id.id
    }

    override fun MiscEducationModel.listener() {
        inputMiscEducation.listener { if (it == miscellaneous || isIncompleted) invalid() else valid(createByDefault()) }
//        inputMiscEducation.listener { actions.value = MiscEducationChange(it) }
    }

    override fun valid(byDefault: MiscEducationDefault) {
        btnCancel.enableCancel(byDefault)
        btnSave.enableSave(byDefault)
    }

    override fun invalid() {
        invisible(btnCancel, btnSave)
    }

    override fun MiscEducationModel.newIncompled() {
        btnCancel.enableCancel(createByDefault())
        invisible(btnRemove, btnSave)
    }

    override fun MiscEducationModel.newCompleted() {
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnRemove.invisible()
    }

    override fun MiscEducationModel.newNotModified() {
        invisible(btnRemove, btnSave, btnCancel)
    }

    override fun MiscEducationModel.persistedIncompled() {
        btnRemove.enableRemove(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnSave.invisible()
    }

    override fun MiscEducationModel.persistedCompleted() {
        btnRemove.enableRemove(createByDefault())
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
    }

    override fun MiscEducationModel.persistedNotModified() {
        btnRemove.enableRemove(createByDefault())
        invisible(btnCancel, btnSave)
    }

    override fun MaterialButton.enableRemove(model: MiscEducationDefault) =
        enabled { actions.value = Remove(model.id) }

    override fun MaterialButton.enableSave(model: MiscEducationDefault) =
        enabled { actions.value = Save(model.createResponse()) }

    override fun MaterialButton.enableCancel(byDefault: MiscEducationDefault) =
        enabled {
            miscEducationC = byDefault.miscEducation
            invisible()
        }

    override fun MiscEducationDefault.createResponse(): MiscEducationComponentResponse =
        MiscEducationComponentResponse(id = id, miscEducation = miscEducationC)
}