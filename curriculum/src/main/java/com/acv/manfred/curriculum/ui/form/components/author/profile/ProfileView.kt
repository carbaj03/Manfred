package com.acv.manfred.curriculum.ui.form.components.author.profile

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import arrow.core.None
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.presentation.form.component.author.profile.*
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentAction.Remove
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponentAction.Save
import com.acv.manfred.curriculum.presentation.form.component.common.Actionable
import com.acv.manfred.curriculum.presentation.form.component.common.Completed
import com.acv.manfred.curriculum.presentation.form.component.common.Incompleted
import com.acv.manfred.curriculum.presentation.form.component.common.Modified
import com.acv.manfred.curriculum.ui.form.components.DateFormatter
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.uikit.input.DateModel
import com.acv.uikit.input.Input
import com.acv.uikit.invisible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_actions.view.*
import kotlinx.android.synthetic.main.component_profile.view.*
import java.util.*

class ProfileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr),
    Actionable<ProfileComponentAction>,
    ProfileComponent,
    DateFormatter {

    init {
        inflate(context, R.layout.component_profile, this)
        orientation = VERTICAL
    }

    private var nameC
        get() = inputName.value
        set(value) {
            inputName.value = value
        }

    private var birthdayC
        get() = inputBirthday.value
        set(value) {
            inputBirthday.value = value
        }

    private var yearsOfExperienceC
        get() = inputYearsOfExperience.value
        set(value) {
            inputYearsOfExperience.value = value
        }


    override val state: ObservableValidation = MutableLiveData()

    override val actions: MutableLiveData<ProfileComponentAction> = MutableLiveData()

    override val isModified: Modified
        get() = when {
            inputName.isValid && inputBirthday.isValid && inputYearsOfExperience.isValid -> Completed
            else -> Incompleted
        }

    override fun ProfileModel.createByDefault(): ProfileDefault =
        ProfileDefault(
            id = id,
            name = nameC,
            birthday = birthdayC,
            yearsOfExperience = yearsOfExperienceC.toFloat(),
            image = None,
            publicLinks = listOf(),
            roles = listOf()
        )

    override fun ProfileModel.renderFields() {
        nameC = name
        birthdayC = birthday
        yearsOfExperienceC = yearsOfExperience.toString()

        val cal = Calendar.getInstance()
        inputBirthday.render(DateModel(birthday) { birthday.createCalendarFromFormatter().fold({ cal.createDialog(inputBirthday)(context) }, { it.createDialog(inputBirthday)(context) }) })
    }

    override fun ProfileModel.listener() {
        inputName.listener { if (it == name || isIncompleted) invalid() else valid(createByDefault()) }
        inputBirthday.listener { if (it == birthday || isIncompleted) invalid() else valid(createByDefault()) }
        inputYearsOfExperience.listener { if (it == yearsOfExperience.toString() || isIncompleted) invalid() else valid(createByDefault()) }
    }

    override fun valid(byDefault: ProfileDefault) {
        btnCancel.enableCancel(byDefault)
        btnSave.enableSave(byDefault)
    }

    override fun invalid() {
        invisible(btnCancel, btnSave)
    }

    override fun ProfileModel.newIncompled() {
        btnCancel.enableCancel(createByDefault())
        invisible(btnRemove, btnSave)
    }

    override fun ProfileModel.newCompleted() {
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnRemove.invisible()
    }

    override fun ProfileModel.newNotModified() {
        invisible(btnRemove, btnSave, btnCancel)
    }

    override fun ProfileModel.persistedIncompled() {
        btnRemove.enableRemove(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnSave.invisible()
    }

    override fun ProfileModel.persistedCompleted() {
        btnRemove.enableRemove(createByDefault())
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
    }

    override fun ProfileModel.persistedNotModified() {
        btnRemove.enableRemove(createByDefault())
        invisible(btnCancel, btnSave)
    }

    override fun MaterialButton.enableRemove(model: ProfileDefault) =
        enabled { actions.value = Remove(model.id) }

    override fun MaterialButton.enableSave(model: ProfileDefault) =
        enabled { actions.value = Save(model.createResponse()) }

    override fun MaterialButton.enableCancel(byDefault: ProfileDefault) =
        enabled {
            nameC = byDefault.name
            birthdayC = byDefault.birthday
            yearsOfExperienceC = byDefault.yearsOfExperience.toString()
            invisible()
        }

    override fun ProfileDefault.createResponse(): ProfileComponentResponse =
        ProfileComponentResponse(
            id = id,
            name = nameC,
            birthday = birthdayC,
            yearsOfExperience = yearsOfExperienceC.toFloat(),
            image = None,
            publicLinks = listOf(),
            roles = listOf()
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