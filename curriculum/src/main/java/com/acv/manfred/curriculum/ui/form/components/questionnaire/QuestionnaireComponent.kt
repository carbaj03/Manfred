package com.acv.manfred.curriculum.ui.form.components.questionnaire

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.form.QuestionnaireModel
import com.acv.manfred.curriculum.ui.form.components.common.ComponentAction
import com.acv.manfred.curriculum.ui.form.components.common.ObservableAction
import com.acv.manfred.curriculum.ui.form.components.common.Remove
import com.acv.manfred.curriculum.ui.form.components.common.Save
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.component_questionnaire.view.*

class QuestionnaireComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var model = QuestionnaireModel("", "", "", Save(listOf()))
    val state: ObservableAction = MutableLiveData()

    init {
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.component_questionnaire, this, true)
        orientation = VERTICAL
    }

    fun render(model: QuestionnaireModel): QuestionnaireComponent {
        this.model = model
        inputQuestion.value = model.question!!
        inputAnswer.value = model.answer!!
        model.action.render()
        return this
    }

    private fun ComponentAction.render() =
        when (this) {
            is Remove -> {
                btnAction.text = "Remove"
                btnAction onClick { state.value = Remove(model.id) }
            }
            is Save -> {
                btnAction.text = "Save"
                btnAction onClick {
                    model.answer = inputAnswer.value
                    model.question = inputQuestion.value
                    state.value = Save(listOf(model))
                }
            }
        }


}