package com.acv.manfred.curriculum.ui.form.languaje

import android.view.ViewGroup
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkLanguageFetcher
import com.acv.manfred.curriculum.data.gateway.NetworkProficiencyFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkLanguageFetcher
import com.acv.manfred.curriculum.data.gateway.networkProficiencyFetcher
import com.acv.manfred.curriculum.presentation.form.component.language.LanguageContainer
import com.acv.manfred.curriculum.presentation.form.component.language.LanguageModel
import com.acv.manfred.curriculum.presentation.form.component.language.StateLanguage
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.activity.snack
import com.acv.manfred.curriculum.ui.common.arch.LanguageViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.arch.viewModelProviders
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.form.components.common.*
import com.acv.manfred.curriculum.ui.form.components.language.LanguageComponent
import com.acv.manfred.curriculum.ui.operations.LanguageViewOperations
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_language.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class LanguageFragment : BaseFragment(), LanguageContainer {
    override val adapter: ComponentAdapter<LanguageModel> = createAdapter()

    override val container: ViewGroup get() = language_container

    override fun createComponent(): LanguageComponent =
        LanguageComponent(baseActivity).apply {
            observe { actions } map { model { StateLanguage.Action(this@map).run() } }
        }

    override val model by lazy {
        viewModelProviders<LanguageViewModel>(LanguageViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object :
            LanguageViewOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkLanguageFetcher<ApiModule> by ApiModule.networkLanguageFetcher(baseActivity),
            NetworkProficiencyFetcher<ApiModule> by ApiModule.networkProficiencyFetcher(baseActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_language

    override fun onCreate() {
        observe { languages } map { swap()}
        observe { validation } map { validate() }
        model { StateLanguage.Load.run() }
    }

    private fun ComponentValidation.validate() =
        when (this) {
            is Valid ->
                baseActivity.fab {
                    show()
                    onClick { model { StateLanguage.Add.run() } }
                }
            is Invalid -> baseActivity.fab { hide() }
            is Error -> baseActivity.snack(msg)
        }
}