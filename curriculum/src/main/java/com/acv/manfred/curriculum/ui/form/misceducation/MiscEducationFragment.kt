package com.acv.manfred.curriculum.ui.form.misceducation

import android.view.ViewGroup
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.io.async.async
import arrow.fx.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkMiscEducationFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.network.fetcher.networkMiscEducationFetcher
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationComponent
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationContainer
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.StateMiscEducation
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.activity.snack
import com.acv.manfred.curriculum.ui.common.arch.MiscEducationViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.arch.viewModelProviders
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.form.components.common.*
import com.acv.manfred.curriculum.ui.form.components.miscEducation.MiscEducationView
import com.acv.manfred.curriculum.ui.form.components.miscEducation.MiscEducationViewModel
import com.acv.manfred.curriculum.ui.operations.MiscEducationViewOperations
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_misc_education.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


class MiscEducationFragment : BaseFragment(), MiscEducationContainer {
    override val adapter: ComponentAdapter<MiscEducationModel> = createAdapter()

    override val container: ViewGroup get() = misc_education_container

    override fun createComponent(): MiscEducationComponent =
        MiscEducationView(baseActivity).apply {
            observe { actions } map { model { StateMiscEducation.Action(this@map).run() } }
        }

    override val model: MiscEducationViewModel by lazy {
        viewModelProviders<MiscEducationViewModel>(MiscEducationViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : MiscEducationViewOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkMiscEducationFetcher<ApiModule> by ApiModule.networkMiscEducationFetcher(baseActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_misc_education

    override fun onCreate() {
        baseActivity.fab {
            show()
            setImageResource(R.drawable.ic_favorite)
            onClick { model { StateMiscEducation.Add.run() } }
        }
        observe { miscEducations } map { swap { a, b -> a.id.id == b.id.id } }
        observe { validation } map { validate() }
        model { StateMiscEducation.Load.run() }
    }

    private fun ComponentValidation.validate() =
        when (this) {
            is Valid ->
                baseActivity.fab {
                    show()
                    onClick { model { StateMiscEducation.Add.run() } }
                }
            is Invalid -> baseActivity.fab { hide() }
            is Error -> baseActivity.snack(msg)
        }

}


