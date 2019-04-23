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
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.ui.common.arch.LanguageViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.observe
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders
import com.acv.uikit.common.Component
import com.acv.uikit.input.Input
import com.acv.uikit.input.SpinnerModel
import com.acv.uikit.popup.Popup
import com.acv.uikit.popup.PopupAdapter
import com.acv.uikit.popup.PopupModel
import kotlinx.android.synthetic.main.view_language.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class LanguageFragment : BaseFragment() {

    private val model by lazy {
        viewModelProviders<LanguageViewModel>(LanguageViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : RequestOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkFetcher<ApiModule> by ApiModule.networkFetcher(compatActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_language

    override fun onCreate() {
        observe { model.proficiencies }.map { showProficiencies(it) }

        model.getProficiencies()
    }

    fun showProficiencies(cv: List<Proficiency>) {
        inputProficiency.render(SpinnerModel("") { createPopup(cv.map { PopupAdapter(it.value) }, inputProficiency)() })
    }

    fun createPopup(list: List<PopupAdapter>, input: Input): () -> Component<PopupModel> = {
        Popup(compatActivity).render(PopupModel(input, list) { x ->
            input.render(SpinnerModel(x.title) { createPopup(list, input)() })
        })
    }
}