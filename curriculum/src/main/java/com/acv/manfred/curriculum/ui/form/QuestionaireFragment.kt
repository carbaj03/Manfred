package com.acv.manfred.curriculum.ui.form

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.RequestOperations
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkFetcher
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.ui.common.arch.QuestionaireViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.observe
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_questionaire.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class QuestionaireFragment : BaseFragment() {
    private val model by lazy {
        viewModelProviders<QuestionaireViewModel>(QuestionaireViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : RequestOperations<ForIO, ApiModule>,
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

        btnAdd onClick {
            model.save(listOf(Questionnaire(question = inputQuestion.value, answer = inputAnswer.value)))
        }
    }

    private fun showQuestionnarie(it: List<Questionnaire>) {
        it.first().run {
            inputQuestion.value= question!!
            inputAnswer.value = answer!!
        }
    }


}


