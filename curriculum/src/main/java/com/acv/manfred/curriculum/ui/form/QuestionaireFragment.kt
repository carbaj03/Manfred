package com.acv.manfred.curriculum.ui.form

import android.view.ViewGroup
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkFetcher
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.activity.snack
import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.common.arch.QuestionaireViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.arch.viewModelProviders
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.form.components.common.*
import com.acv.manfred.curriculum.ui.form.components.common.Error
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireComponent
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.ui.operations.ViewOperations
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_questionaire.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


interface QuestionnaireContainer :
    ComponentContainer<QuestionnaireModel, QuestionnaireComponent>,
    Observable<QuestionaireViewModel>

class QuestionaireFragment : BaseFragment(), QuestionnaireContainer {

    override val adapter: ComponentAdapter<QuestionnaireModel> = createAdapter()

    override val container: ViewGroup get() = questionnaire_container

    override fun createComponent(): Component<QuestionnaireModel> =
        QuestionnaireComponent(baseActivity).apply {
            observe { actions } map { model { Action(this@map).run() } }
        }

    override fun getLayout(): Int =
        R.layout.view_questionaire

    private val dependencies by lazy {
        object : ViewOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkFetcher<ApiModule> by ApiModule.networkFetcher(baseActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override val model: QuestionaireViewModel by lazy {
        viewModelProviders<QuestionaireViewModel>(QuestionaireViewModelFactory(dependencies))
    }

    override fun onCreate() {
        observe { questionnaire } map { swap { a, b -> a == b } }
        observe { validation } map { validate() }
        model { Load.run() }
    }

    private fun ComponentValidation.validate() =
        when (this) {
            is Valid ->
                baseActivity.fab {
                    show()
                    onClick { model { Add.run() } }
                }
            is Invalid -> baseActivity.fab { hide() }
            is Error -> baseActivity.snack(msg)
        }

}