package com.acv.manfred.curriculum.ui.form

import androidx.recyclerview.widget.ListUpdateCallback
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
import com.acv.manfred.curriculum.ui.form.components.questionnaire.*
import com.acv.manfred.curriculum.ui.operations.ViewOperations
import com.acv.uikit.adapterModel.Adapter
import com.acv.uikit.adapterModel.CGAdapter
import com.acv.uikit.adapterModel.POSITION
import com.acv.uikit.adapterModel.autoNotify
import com.acv.uikit.chip.ChipModel
import com.acv.uikit.chip.ObserveChip
import com.acv.uikit.onClick
import com.google.android.material.chip.Chip
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
        observe { model.questionnaire }.map {
            swap(it) { a, b -> a.id == b.id }
//            it.showQuestionnarie()
        }
        observe { model.fab }.map { it.buttonAdd() }

        model.run { Load.state() }
    }

    private fun ComponentValidation.buttonAdd() =
        when (this) {
            Valid ->
                compatActivity.fab {
                    show()
                    onClick { this@QuestionaireFragment.model.run { Add.state() } }
                }
            Invalid -> compatActivity.fab { hide() }
        }

    private fun List<QuestionnaireModel>.showQuestionnarie() {
        questionnaire_container.removeAllViews()
        map {
            val c = QuestionnaireComponent(compatActivity)
            observe { c.actions } map { model.run { Action(it).state() } }
            questionnaire_container.addView(c.renderType(it))
        }
    }


    private var adapter: QuestionnaireAdapter = QuestionnaireAdapter(observable(), mutableListOf())

    fun swap(newItems: List<QuestionnaireModel>, compare: (QuestionnaireModel, QuestionnaireModel) -> Boolean) {
        adapter.swap(newItems, compare)
    }

    private fun getChip(position: Int): QuestionnaireComponent =
        questionnaire_container.getChildAt(position) as QuestionnaireComponent

    private fun observable(): ObserveQuestionnaire<QuestionnaireModel> = object : ObserveQuestionnaire<QuestionnaireModel> {
        override fun remove(position: Int) {
            questionnaire_container.removeViewAt(position)
        }

        override fun insert(m: QuestionnaireModel) {
            val c = QuestionnaireComponent(compatActivity)
            observe { c.actions } map { model.run { Action(it).state() } }
            questionnaire_container.addView(c.renderType(m))
        }

        override fun moved(fromPosition: Int, toPosition: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun change(position: Int, m: QuestionnaireModel) {
            getChip(position).renderType(m)
        }
    }
}

interface ObserveQuestionnaire<M> {
    fun remove(position: Int)
    fun insert(m: M)
    fun moved(fromPosition: Int, toPosition: Int)
    fun change(position: Int, m: M)
}

class QuestionnaireAdapter(
    private var observable: ObserveQuestionnaire<QuestionnaireModel>,
    private var items: MutableList<QuestionnaireModel>
) : Adapter<QuestionnaireModel>, ListUpdateCallback {
    override fun set(position: POSITION, item: QuestionnaireModel) {
        items[position] = item
    }

    override fun swap(newItems: List<QuestionnaireModel>, compare: (QuestionnaireModel, QuestionnaireModel) -> Boolean) {
        val diffResult = autoNotify(items, newItems, compare)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        observable.change(position, items[position])
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        observable.moved(fromPosition, toPosition)
    }

    override fun onInserted(position: Int, count: Int) {
        for (a in 0 until count)
            observable.insert(items[position + a])
    }

    override fun onRemoved(position: Int, count: Int) {
        for (a in (count - 1)..0)
            observable.remove(position)
    }
}

//interface a{
//    private val state: MediatorState = MediatorLiveData()
//
//    private fun List<Validable>.combine(): ComponentValidation =
//        firstOrNull { it.state.value is Valid }?.state?.value ?: defaultState()
//
//    private fun List<Validable>.defaultState(): ComponentValidation =
//        if (forAll { it.state.value == Valid }) Valid else Invalid
//
//    private val component: List<Validable>
//        get() {
//            val temp: MutableList<Validable> = mutableListOf()
//            container().forEachChild { temp.add(it as Validable) }
//            return temp
//        }
//
//    private fun container(): LinearLayout = (questionnaire_container as LinearLayout)
//
//    inline fun ViewGroup.forEachChild(action: (View) -> Unit) {
//        for (i in 0 until childCount) {
//            action(getChildAt(i))
//        }
//    }
//}

