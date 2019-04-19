package com.acv.manfred.curriculum.ui.form

import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.example.RoleProfile
import com.acv.manfred.curriculum.data.gateway.RequestOperations
import com.acv.manfred.curriculum.data.gateway.datasource.ApiModule
import com.acv.manfred.curriculum.ui.common.arch.FormViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.observe
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders
import com.acv.uikit.popup.PopupAdapter
import kotlinx.android.synthetic.main.view_author.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class AuthorFragment : BaseFragment() {
    private val model by lazy { viewModelProviders<FormViewModel>(FormViewModelFactory(dependencies)) }

    private val dependencies by lazy {
        object : RequestOperations<ForIO>, Async<ForIO> by IO.async() {
            override val network: ApiModule = ApiModule()
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.view_author

    override fun onCreate() {
        observe { model.cv }.map { showCv(it) }
        observe { model.roles }.map { showRoles(it) }

        chaPublicLinks.action(compatActivity.supportFragmentManager)

        model.getCv()
        model.getRoles()
    }

    fun showCv(cv: Example) {
        inputFullName.value = cv.author.profile.name
    }

    fun showRoles(cv: List<RoleProfile>) {
        chaRoles.action(cv.map { PopupAdapter(it.value()) })
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


