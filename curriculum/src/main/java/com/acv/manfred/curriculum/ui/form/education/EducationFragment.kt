package com.acv.manfred.curriculum.ui.form.education

import android.view.ViewGroup
import arrow.effects.ForIO
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkEducationFetcher
import com.acv.manfred.curriculum.data.gateway.network.fetcher.networkEducationFetcher
import com.acv.manfred.curriculum.presentation.form.component.education.EducationComponent
import com.acv.manfred.curriculum.presentation.form.component.education.EducationContainer
import com.acv.manfred.curriculum.presentation.form.component.education.EducationModel
import com.acv.manfred.curriculum.presentation.form.component.education.StateEducation
import com.acv.manfred.curriculum.presentation.operation.EducationUseCase
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.activity.snack
import com.acv.manfred.curriculum.ui.common.arch.EducationViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.arch.viewModelProviders
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.form.components.common.*
import com.acv.manfred.curriculum.ui.form.components.education.EducationView
import com.acv.manfred.curriculum.ui.form.components.education.EducationViewModel
import com.acv.manfred.curriculum.ui.operations.EducationViewOperations
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_education.*

class EducationFragment : BaseFragment(), EducationContainer {

    override val adapter: ComponentAdapter<EducationModel> = createAdapter()
    override val container: ViewGroup get() = education_container

    override fun createComponent(): EducationComponent =
        EducationView(baseActivity).apply {
            observe { actions } map { model { StateEducation.Action(this@map).run() } }
        }

    override val model: EducationViewModel by lazy {
        viewModelProviders<EducationViewModel>(EducationViewModelFactory(dependencies))
    }

    private val dependencies : EducationUseCase by lazy {
        object : EducationViewOperations<ForIO, ApiModule>,
            NetworkEducationFetcher<ApiModule> by ApiModule.networkEducationFetcher(baseActivity) {}
    }

    override fun getLayout(): Int = R.layout.view_education

    override fun onCreate() {
        baseActivity.fab {
            show()
            setImageResource(R.drawable.ic_favorite)
            onClick { model { StateEducation.Add.run() } }
        }
        observe { educations } map { swap { a, b -> a.id.id == b.id.id } }
        observe { validation } map { validate() }
        model { StateEducation.Load.run() }
    }

    private fun ComponentValidation.validate() =
        when (this) {
            is Valid ->
                baseActivity.fab {
                    show()
                    onClick { model { StateEducation.Add.run() } }
                }
            is Invalid -> baseActivity.fab { hide() }
            is Error -> baseActivity.snack(msg)
        }
}