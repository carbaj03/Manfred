package com.acv.manfred.curriculum.ui.form

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.MediatorLiveData
import arrow.data.extensions.list.foldable.forAll
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkFetcher
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.arch.QuestionaireViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.observe
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders
import com.acv.manfred.curriculum.ui.form.components.common.MediatorState
import com.acv.manfred.curriculum.ui.form.components.questionnaire.*
import com.acv.manfred.curriculum.ui.operations.ViewOperations
import com.acv.uikit.onClick
import com.itextpdf.awt.geom.misc.HashCode.combine
import kotlinx.android.synthetic.main.view_questionaire.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

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
        observe { model.fab }.map { it.buttonAdd() }

        model.run { Load.state() }
    }

    private fun ComponentValidation.buttonAdd() =
        when(this){
            Valid ->
                compatActivity.fab {
                    show()
                    onClick { this@QuestionaireFragment.model.run { Add.state() } }
                }
            Invalid -> compatActivity.fab { hide() }
        }

    private val state: MediatorState = MediatorLiveData()

    private fun List<Validable>.combine(): ComponentValidation =
        firstOrNull { it.state.value is Valid }?.state?.value ?: defaultState()

    private fun List<Validable>.defaultState(): ComponentValidation =
        if (forAll { it.state.value == Valid }) Valid else Invalid

    private val component: List<Validable>
        get() {
            val temp: MutableList<Validable> = mutableListOf()
            container().forEachChild { temp.add(it as Validable) }
            return temp
        }

    private fun container(): LinearLayout = (questionnaire_container as LinearLayout)

    inline fun ViewGroup.forEachChild(action: (View) -> Unit) {
        for (i in 0 until childCount) {
            action(getChildAt(i))
        }
    }

    private fun showQuestionnarie(it: List<QuestionnaireModel>) {
        questionnaire_container.removeAllViews()
        it.map {
            val c = QuestionnaireComponent(compatActivity)
            observe { c.actions } map { model.run { Action(it).state() } }
            questionnaire_container.addView(c.render(it))
        }

//        component.combine().buttonAdd()
//
//        component.map { state.addSource(it.state) { state.value = component.combine() } }
//
//        observe { state } map { it.buttonAdd() }
    }
}


