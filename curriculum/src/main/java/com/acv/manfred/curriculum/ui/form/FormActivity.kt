package com.acv.manfred.curriculum.ui.form

import arrow.core.None
import arrow.core.some
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.async.async
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.example.RoleProfile
import com.acv.manfred.curriculum.data.gateway.RequestOperations
import com.acv.manfred.curriculum.data.gateway.datasource.ApiModule
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance
import com.acv.manfred.curriculum.ui.common.activity.observe
import com.acv.manfred.curriculum.ui.common.activity.viewModelProviders
import com.acv.manfred.curriculum.ui.common.arch.FormViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.map
import com.acv.uikit.chip.ChipModel
import com.acv.uikit.chip.Closelable
import com.acv.uikit.popup.Popup
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class FormActivity : BaseActivity() {

    //    val rvadapterModel by lazy {
    //        RVAdapter<MediaAdapter> { video, v ->
    //            when (video) {
    //                is VideoAdapter -> VideoAdapterHolder(v)
    //                is AudioAdapter -> AudioAdapterHolder(v)
    //            }
    //        }
    //    }

    var temp = listOf(ChipModel("0", "asdfsa", None, None))

    private val model by lazy { viewModelProviders<FormViewModel>(FormViewModelFactory(dependencies)) }

    private val dependencies by lazy {
        object : RequestOperations<ForIO>, Async<ForIO> by IO.async() {
            override val network: ApiModule = ApiModule()
            override val main: CoroutineContext = Dispatchers.Main
            override val ctx: CoroutineContext = Dispatchers.IO
        }
    }

    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        //        rvVideo.layoutManager = LinearLayoutManager(this)
        //        rvVideo.adapter = rvadapterModel
        chaRoles.swap(temp) { a, b -> a.id == b.id }
        chaRoles.action { Popup(this).show(chaRoles) }

        observe { model.cv }.map { showCv(it) }
        observe { model.roles }.map { showRoles(it) }

        model.getCv()
        model.getRoles()

    }

    fun showCv(cv: Example) {
        inputFullName.value = cv.author.profile.name
    }

    fun showRoles(cv: List<RoleProfile>) {
        chaRoles.swap(cv.map { ChipModel(it.name, it.value()) }) { a, b -> a.id == b.id }
    }

    private fun addCategory() {
        temp = temp.plus(ChipModel(temp.size.toString(), "other", Closelable { removeCategory(it) }.some(), None))
        chaRoles.swap(temp) { a, b -> a.id == b.id }
    }

    private fun removeCategory(it: ChipModel) {
        temp = temp.minus(it)
        chaRoles.swap(temp) { a, b -> a.id == b.id }
    }
}