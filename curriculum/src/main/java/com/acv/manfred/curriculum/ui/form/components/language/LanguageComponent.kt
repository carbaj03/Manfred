package com.acv.manfred.curriculum.ui.form.components.language

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.domain.model.GenerateId
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.presentation.form.component.common.*
import com.acv.manfred.curriculum.presentation.form.component.language.*
import com.acv.manfred.curriculum.presentation.form.component.language.Remove
import com.acv.manfred.curriculum.presentation.form.component.language.Save
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentValidation
import com.acv.manfred.curriculum.ui.form.components.common.Invalid
import com.acv.manfred.curriculum.ui.form.components.common.ObservableValidation
import com.acv.manfred.curriculum.ui.form.components.common.Valid
import com.acv.uikit.common.textWatcher
import com.acv.uikit.input.Input
import com.acv.uikit.input.SpinnerModel
import com.acv.uikit.invisible
import com.acv.uikit.onClick
import com.acv.uikit.popup.Popup
import com.acv.uikit.popup.PopupAdapter
import com.acv.uikit.popup.PopupModel
import com.acv.uikit.visible
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.component_language.view.*

class LanguageComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Validable, Actionable<LanguageComponentAction>, Component<LanguageModel> {
    override val state: ObservableValidation = MutableLiveData()
    override val actions: MutableLiveData<LanguageComponentAction> = MutableLiveData()

    private val Input.isValid: Boolean
        get() = value.isNotBlank()

    private val Input.isInvalid: Boolean
        get() = value.isBlank()

    private val isModified
        get() = when {
            inputLanguage.isValid && inputProficiency.isValid -> Completed
            else -> Incompleted
        }

    private val isCompleted
        get() = isModified is Completed

    private val isIncompleted
        get() = isModified is Incompleted

    init {
        inflate(context, R.layout.component_language, this)
        orientation = VERTICAL
        initActions()
    }

    override fun renderType(model: LanguageModel): LanguageComponent {
        model.run {
            state.value = getState(componentType)
            renderFields()
            renderType()
            listener()
        }

        return this
    }

    private fun getState(componentType: ComponentType): ComponentValidation =
        if (componentType is Persisted) Valid else Invalid

    private fun LanguageModel.createByDefault(): ByDefault =
        ByDefault(id = id, language = language, proficiency = proficiency, proficiencies = proficiencies)

    private fun LanguageModel.renderFields() {
        inputLanguage.value = language
        showProficiencies()
        info.text = id.id
    }

    private fun initActions() {
        btnRemove.invisible()
        btnSave.invisible()
        btnCancel.invisible()
    }

    private fun LanguageModel.listener() {
        inputLanguage.listener(language, createByDefault())
        inputProficiency.listener(proficiency, createByDefault())
    }

    private fun Input.listener(toCompare: String?, byDefault: ByDefault) {
        watch(textWatcher {
            if (value == toCompare || isIncompleted) {
                this@LanguageComponent.btnCancel.invisible()
                this@LanguageComponent.btnSave.invisible()
            } else {
                this@LanguageComponent.btnCancel.cancel(byDefault)
                this@LanguageComponent.btnSave.save(byDefault.id, byDefault.proficiencies)
            }
        })
    }

    private fun LanguageModel.renderType() =
        when (componentType) {
            is Persisted -> renderState()
            is New -> renderNew()
        }

    private fun LanguageModel.renderNew() {
        when (componentType.componentState) {
            Incompleted ->
                btnCancel.cancel(createByDefault())
            Completed -> {
                btnSave.save(id, proficiencies)
                btnCancel.cancel(createByDefault())
            }
            NotModified -> {
            }
        }
    }

    private fun LanguageModel.renderState(): Unit =
        when (componentType.componentState) {
            Incompleted -> {
                btnRemove.remove(id)
                btnCancel.cancel(createByDefault())
            }
            Completed -> {
                btnRemove.remove(id)
                btnSave.save(id, proficiencies)
                btnCancel.cancel(createByDefault())
            }
            NotModified -> btnRemove.remove(id)
        }

    private fun MaterialButton.remove(id: GenerateId): Unit =
        action { actions.value = Remove(id) }

    private fun MaterialButton.save(id: GenerateId, proficiencies: List<ProficiencyModel>): Unit =
        action { actions.value = Save(createResponse(id, proficiencies)) }

    private fun createResponse(id: GenerateId, proficiencies: List<ProficiencyModel>): LanguageComponentResponse =
        LanguageComponentResponse(
            id = id,
            language = this@LanguageComponent.inputLanguage.value,
            proficiency = Proficiency(proficiencies[proficiency].value)
        )

    private fun MaterialButton.cancel(byDefault: ByDefault): Unit =
        action {
            this@LanguageComponent.inputLanguage.value = byDefault.language
            this@LanguageComponent.inputProficiency.value = byDefault.proficiency
            invisible()
        }

    private fun MaterialButton.action(f: () -> Unit) {
        visible()
        onClick { f() }
    }

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