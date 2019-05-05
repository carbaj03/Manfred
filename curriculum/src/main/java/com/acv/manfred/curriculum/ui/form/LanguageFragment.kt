package com.acv.manfred.curriculum.ui.form

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkFetcher
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.ui.common.arch.LanguageViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.arch.viewModelProviders
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.operations.ViewOperations
import com.acv.uikit.common.Component
import com.acv.uikit.input.Input
import com.acv.uikit.input.SpinnerModel
import com.acv.uikit.popup.Popup
import com.acv.uikit.popup.PopupAdapter
import com.acv.uikit.popup.PopupModel
import kotlinx.android.synthetic.main.view_language.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class LanguageFragment : BaseFragment(), Observable<LanguageViewModel> {
    override val model by lazy {
        viewModelProviders<LanguageViewModel>(LanguageViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : ViewOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkFetcher<ApiModule> by ApiModule.networkFetcher(baseActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_language

    override fun onCreate() {
        observe { proficiencies } map { showProficiencies() }
        model.getProficiencies()
    }

    private fun List<Proficiency>.showProficiencies() {
        inputProficiency.render(SpinnerModel("") { createPopup(this.map { PopupAdapter(it.value) }, inputProficiency)() })
    }

    private fun createPopup(list: List<PopupAdapter>, input: Input): () -> Component<PopupModel> = {
        Popup(baseActivity).render(PopupModel(input, list) { x ->
            input.render(SpinnerModel(x.title) { createPopup(list, input)() })
        })
    }
}