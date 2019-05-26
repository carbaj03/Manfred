package com.acv.manfred.curriculum.ui.form.components.language

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.language.*
import com.acv.manfred.curriculum.presentation.form.component.language.Remove
import com.acv.manfred.curriculum.presentation.form.component.language.Save
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.uikit.input.Input
import com.acv.uikit.input.SpinnerModel
import com.acv.uikit.invisible
import com.acv.uikit.popup.Popup
import com.acv.uikit.popup.PopupAdapter
import com.acv.uikit.popup.PopupModel
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_actions.view.*
import kotlinx.android.synthetic.main.component_language.view.*


class LanguageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    LinearLayout(context, attrs, defStyleAttr),
    Validable,
    Actionable<LanguageComponentAction>,
    LanguageComponent {

    init {
        inflate(context, R.layout.component_language, this)
        orientation = VERTICAL
    }

    override val state: ObservableValidation = MutableLiveData()
    override val actions: MutableLiveData<LanguageComponentAction> = MutableLiveData()

    private var languageC
        get() = inputLanguage.value
        set(value) {
            inputLanguage.value = value
        }

    private var proficiencyC
        get() = inputProficiency.value
        set(value) {
            inputProficiency.value = value
        }

    override val isModified: ComponentState
        get() = when {
            inputLanguage.isValid && inputProficiency.isValid -> Completed
            else -> Incompleted
        }

    override fun LanguageModel.createByDefault(): LanguageDefault =
        LanguageDefault(id = id, language = language, proficiency = proficiency, proficiencies = proficiencies)

    override fun LanguageModel.renderFields() {
        languageC = language
        showProficiencies()
        info.text = id.id
    }

    override fun LanguageModel.listener() {
        inputLanguage.listener { if (it == language || isIncompleted) invalid() else valid(createByDefault()) }
        inputProficiency.listener { if (it == proficiency || isIncompleted) invalid() else valid(createByDefault()) }
    }

    override fun invalid() {
        invisible(btnCancel, btnSave)
    }

    override fun valid(byDefault: LanguageDefault) {
        btnCancel.enableCancel(byDefault)
        btnSave.enableSave(byDefault)
    }

    override fun LanguageModel.newIncompled() {
        btnCancel.enableCancel(createByDefault())
        invisible(btnRemove, btnSave)
    }

    override fun LanguageModel.newCompleted() {
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
        btnRemove.invisible()
    }

    override fun LanguageModel.newNotModified() {
        invisible(btnRemove, btnSave, btnCancel)
    }

    override fun LanguageModel.persistedIncompled() {
        btnRemove.enableRemove(createByDefault())
        btnCancel.enableCancel(createByDefault())
    }

    override fun LanguageModel.persistedCompleted() {
        btnRemove.enableRemove(createByDefault())
        btnSave.enableSave(createByDefault())
        btnCancel.enableCancel(createByDefault())
    }

    override fun LanguageModel.persistedNotModified() {
        btnRemove.enableRemove(createByDefault())
    }

    override fun MaterialButton.enableRemove(model: LanguageDefault) =
        enabled { actions.value = Remove(model.id) }

    override fun MaterialButton.enableSave(model: LanguageDefault) =
        enabled { actions.value = Save(model.createResponse()) }

    override fun MaterialButton.enableCancel(byDefault: LanguageDefault): Unit =
        enabled {
            languageC = byDefault.language
            proficiencyC = byDefault.proficiency
            invisible()
        }

    override fun LanguageDefault.createResponse(): LanguageComponentResponse =
        LanguageComponentResponse(
            id = id,
            language = languageC,
            proficiency = Proficiency(proficiencies[this@LanguageView.proficiency].value)
        )

    var proficiency: Int = 0

    private fun LanguageModel.showProficiencies() {
        inputProficiency.render(spinnerModel(proficiency, proficiencies.map { PopupAdapter(it.value) }.createPopup(inputProficiency)))
    }

    private fun List<PopupAdapter>.createPopup(input: Input): () -> com.acv.uikit.common.Component<PopupModel> =
        { Popup(context).render(input.popupModel(this)) }

    private fun Input.popupModel(list: List<PopupAdapter>): PopupModel =
        PopupModel(this, list) { t, p ->
            proficiency = p
            render(spinnerModel(t.title, list.createPopup(this)))
        }

    private fun spinnerModel(value: String, f: () -> com.acv.uikit.common.Component<PopupModel>): SpinnerModel =
        SpinnerModel(value) { f() }
}