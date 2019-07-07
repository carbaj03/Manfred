package com.acv.manfred.curriculum.ui.form.author

import android.view.ViewGroup
import androidx.recyclerview.widget.ListUpdateCallback
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.network.fetcher.NetworkProfileFetcher
import com.acv.manfred.curriculum.data.gateway.network.fetcher.networkProfileFetcher
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileComponent
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileContainer
import com.acv.manfred.curriculum.presentation.form.component.author.profile.ProfileModel
import com.acv.manfred.curriculum.presentation.form.component.author.profile.StateProfile
import com.acv.manfred.curriculum.presentation.form.component.education.StateEducation
import com.acv.manfred.curriculum.presentation.operation.ProfileUseCase
import com.acv.manfred.curriculum.presentation.operation.ProfileUseCaseAndroid
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.activity.snack
import com.acv.manfred.curriculum.ui.common.arch.ProfileViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.arch.viewModelProviders
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileView
import com.acv.manfred.curriculum.ui.form.components.author.profile.ProfileViewModel
import com.acv.manfred.curriculum.ui.form.components.common.*
import com.acv.manfred.curriculum.ui.operations.ProfileViewOperations
import com.acv.uikit.adapterModel.*
import com.acv.uikit.onClick
import kotlinx.android.synthetic.main.view_author_profile.*

class ProfileFragment : BaseFragment(), ProfileContainer {
//    override val asyncDiffResult: AsyncDiffResult<AndroidDiffer, AndroidUpdatable> = object : AndroidDiffResult {
//        override val differ: AndroidDiffer
//            get() = object : AndroidDiffer {}
//    }

//    override val adapter: AdapterK<AndroidDiffer, AndroidUpdatable> = object : AdapterK <AndroidDiffer, AndroidUpdatable>{
//        override val asyncDiffResult: AsyncDiffResult<AndroidDiffer, AndroidUpdatable> = asyncDiffResult
//    }

    override val container: ViewGroup get() = profileContainer

    override fun createComponent(): ProfileComponent =
        ProfileView(baseActivity).apply {
            observe { actions } map { model { StateProfile.Action(this@map).run() } }
        }

    override val model: ProfileViewModel by lazy {
        viewModelProviders<ProfileViewModel>(ProfileViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : ProfileViewOperations<AsyncDiffResult<AndroidDiffer, AndroidUpdatable>, AndroidDiffer, AndroidUpdatable>,
            NetworkProfileFetcher by ApiModule.networkProfileFetcher(baseActivity),
            AndroidUp, AndroidDiff, AndroidDiffResult {
            override val observeComponent: ObserveComponent<ProfileModel> get() = observable()
            override var oldState: List<ProfileModel> = listOf()
        }
    }

    override fun getLayout(): Int = R.layout.view_author_profile

    override fun onCreate() {
//        observe { profile } map { swip() }
        observe { validation } map { validate() }
        model { StateProfile.Load.run() }

        baseActivity.fab {
            show()
            setImageResource(R.drawable.ic_favorite)
            onClick { model { StateProfile.Add.run() } }
        }
//        observe { cv } map { showCv() }
//        observe { roles } map { showRoles() }
//
//        chaPublicLinks.action(baseActivity.supportFragmentManager)
//
//        model.getCv()
//        model.getRoles()
//
//        when (val ac = baseActivity) {
//            is Actionable -> ac.config {
//                hide()
//            }
//        }
    }

    private fun ComponentValidation.validate() =
        when (this) {
            is Valid -> baseActivity.snack("save")
            is Invalid -> baseActivity.fab { hide() }
            is Error -> {
                baseActivity.fab {
                    show()
                    setImageResource(R.drawable.ic_favorite)
                    onClick { model { StateProfile.Add.run() } }
                }
                baseActivity.snack(msg)
            }
        }

//    private fun Example.showCv() {
//        inputFullName.value = author.profile.name
//    }
//
//    fun List<RoleProfile>.showRoles() {
//        chaRoles.action(map { PopupAdapter(it.value) })
////        chaRoles.swap(cv.map { ChipModel(it.name, it.value()) }) { a, b -> a.id == b.id }
//    }

//    private fun addCategory(title: String) {
//        temp = temp.plus(ChipModel(temp.size.toString(), title, None, Closelable { removeCategory(it) }.some(), None))
//        chaRoles.swap(temp) { a, b -> a.id == b.id }
//    }
//
//    private fun removeCategory(it: ChipModel) {
//        temp = temp.minus(it)
//        chaRoles.swap(temp) { a, b -> a.id == b.id }
//    }

}


