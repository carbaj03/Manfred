package com.acv.manfred.curriculum.ui.form

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkFetcher
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.arch.QuestionaireViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.observe
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders
import com.acv.manfred.curriculum.ui.form.components.common.ComponentAction
import com.acv.manfred.curriculum.ui.form.components.common.Remove
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireComponent
import com.acv.manfred.curriculum.ui.operations.ViewOperations
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_questionaire.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

data class QuestionnaireModel(
    var id: String,
    var question: String?,
    var answer: String?,
    var action: ComponentAction
)

fun QuestionnaireModel.toDomain() : Questionnaire =
        Questionnaire(question = question, answer = answer)

fun Questionnaire.toView(): QuestionnaireModel =
    QuestionnaireModel(id, question, answer, Remove(id))

class QuestionaireFragment : BaseFragment() {
    private val model by lazy {
        viewModelProviders<QuestionaireViewModel>(QuestionaireViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : ViewOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkFetcher<ApiModule> by ApiModule.networkFetcher(compatActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_questionaire

    override fun onCreate() {
        observe { model.questionnaire }.map { showQuestionnarie(it) }

        model.getQuestionnaire()

        compatActivity.fab {
            show()
            onClick {
                model.add()
//                createItem(this@QuestionaireFragment.questionnaire_container.childCount)
//                model.save(listOf(Questionnaire(question = inputQuestion.value, answer = inputAnswer.value)))
            }
        }

//        createItem(questionnaire_container.childCount)

    }

//    fun createItem(count: Int) {
//        questionnaire_container.addView(QuestionnaireComponent(compatActivity))
//        questionnaire_container.addView(MaterialButton(compatActivity).apply {
//            setText("REmove");
//            onClick {
//                model.remove()
//                this@QuestionaireFragment.questionnaire_container?.removeViewAt(count + 1)
//                this@QuestionaireFragment.questionnaire_container?.removeViewAt(count + 2)
//            }
//        })
//    }

    private fun showQuestionnarie(it: List<QuestionnaireModel>) {
        questionnaire_container.removeAllViews()
        it.map {
            val c = QuestionnaireComponent(compatActivity)
            observe { c.state } map { model.action(it) }
            questionnaire_container.addView(c.render(it))
        }
    }


}


