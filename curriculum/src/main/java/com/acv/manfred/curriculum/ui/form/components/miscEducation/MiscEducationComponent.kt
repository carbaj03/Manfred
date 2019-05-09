package com.acv.manfred.curriculum.ui.form.components.miscEducation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.common.MiscEducationComponentAction.Remove
import com.acv.manfred.curriculum.presentation.form.component.common.MiscEducationComponentAction.Save
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.ByDefault
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import com.acv.uikit.common.textWatcher
import com.acv.uikit.input.Input
import com.acv.uikit.invisible
import com.acv.uikit.onClick
import com.acv.uikit.visible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_misc_education.view.*

class MiscEducationComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Validable, Actionable<MiscEducationComponentAction>, Component<MiscEducationModel> {
    override val state: ObservableValidation = MutableLiveData()
    override val actions: MutableLiveData<MiscEducationComponentAction> = MutableLiveData()

    private val Input.isValid: Boolean
        get() = value.isNotBlank()

    private val Input.isInvalid: Boolean
        get() = value.isBlank()

    private val isModified
        get() = when {
            inputMiscEducation.isValid -> Completed
            else -> Incompleted
        }

    private val isCompleted
        get() = isModified is Completed

    private val isIncompleted
        get() = isModified is Incompleted

    init {
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.component_misc_education, this, true)
        orientation = VERTICAL
    }

    override fun renderType(model: MiscEducationModel): MiscEducationComponent {
        val byDefault: ByDefault = model.createByDefault()
        state.value = if (model.componentType is Persisted) Valid else Invalid
        initActions()
        model.renderFields()
        model.renderType(byDefault)
        model.listener(byDefault)
        return this
    }

    private fun MiscEducationModel.createByDefault(): ByDefault =
        ByDefault(id = id, miscEducation = miscellaneous ?: "")

    private fun MiscEducationModel.renderFields() {
        inputMiscEducation.value = miscellaneous ?: ""
        info.text = id
    }

    private fun initActions() {
        btnRemove.invisible()
        btnSave.invisible()
        btnCancel.invisible()
    }

    private fun MiscEducationModel.listener(byDefault: ByDefault) {
        inputMiscEducation.listener(miscellaneous, byDefault)
    }

    private fun Input.listener(toCompare: String?, byDefault: ByDefault) {
        watch(textWatcher {
            if (value == toCompare || isIncompleted) {
                this@MiscEducationComponent.btnCancel.invisible()
                this@MiscEducationComponent.btnSave.invisible()
            } else {
                this@MiscEducationComponent.btnCancel.cancel(byDefault)
                this@MiscEducationComponent.btnSave.save(byDefault.id)
            }
        })
    }

    private fun MiscEducationModel.renderType(byDefault: ByDefault) =
        when (componentType) {
            is Persisted -> when (componentType.componentState) {
                Incompleted -> {
                    btnRemove.remove(id!!)
                    btnCancel.cancel(byDefault)
                }
                Completed -> {
                    btnRemove.remove(id!!)
                    btnSave.save(id)
                    btnCancel.cancel(byDefault)
                }
                NotModified -> btnRemove.remove(id!!)
            }
            is New -> when (componentType.componentState) {
                Incompleted -> btnCancel.cancel(byDefault)
                Completed -> {
                    btnSave.save(id)
                    btnCancel.cancel(byDefault)
                }
                NotModified -> {
                }
            }
        }

    private fun MaterialButton.remove(id: String): Unit =
        action { actions.value = Remove(id) }

    private fun MaterialButton.save(id: String?): Unit =
        action { actions.value = Save(createResponse(id)) }

    private fun createResponse(id: String?): MiscEducationComponentResponse =
        MiscEducationComponentResponse(
            id = id,
            miscEducation = this@MiscEducationComponent.inputMiscEducation.value
        )

    private fun MaterialButton.cancel(byDefault: ByDefault): Unit =
        action {
            this@MiscEducationComponent.inputMiscEducation.value = byDefault.miscEducation
            invisible()
        }

    private fun MaterialButton.action(f: () -> Unit) {
        visible()
        onClick { f() }
    }


}