package com.acv.manfred.curriculum.ui.form

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.gateway.NetworkFetcher
import com.acv.manfred.curriculum.data.gateway.datasource.api.ApiModule
import com.acv.manfred.curriculum.data.gateway.networkFetcher
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.RoleProfile
import com.acv.manfred.curriculum.ui.common.activity.Actionable
import com.acv.manfred.curriculum.ui.common.arch.FormViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.Observable
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders
import com.acv.manfred.curriculum.ui.operations.ViewOperations
import com.acv.uikit.popup.PopupAdapter
import kotlinx.android.synthetic.main.view_author_profile.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ProfileFragment : BaseFragment(), Observable<FormViewModel> {
    override val model by lazy {
        viewModelProviders<FormViewModel>(FormViewModelFactory(dependencies))
    }

    private val dependencies by lazy {
        object : ViewOperations<ForIO, ApiModule>,
            Async<ForIO> by IO.async(),
            NetworkFetcher<ApiModule> by ApiModule.networkFetcher(compatActivity) {
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_author_profile

    override fun onCreate() {
        observe { cv } map { showCv() }
        observe { roles } map { showRoles() }

        chaPublicLinks.action(compatActivity.supportFragmentManager)

        model.getCv()
        model.getRoles()

        when (val ac = compatActivity) {
            is Actionable -> ac.config {
                hide()
            }
        }
    }

    private fun Example.showCv() {
        inputFullName.value = author.profile.name
    }

    fun List<RoleProfile>.showRoles() {
        chaRoles.action(map { PopupAdapter(it.value) })
//        chaRoles.swap(cv.map { ChipModel(it.name, it.value()) }) { a, b -> a.id == b.id }
    }

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


