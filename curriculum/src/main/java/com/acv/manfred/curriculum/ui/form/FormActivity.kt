package com.acv.manfred.curriculum.ui.form

import android.view.MenuItem
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
import com.acv.uikit.popup.PopupAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
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


    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        //        rvVideo.layoutManager = LinearLayoutManager(this)
        //        rvVideo.adapter = rvadapterModel
//        chaRoles.swap(temp) { a, b -> a.id == b.id }


        setSupportActionBar(bottomApp)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AuthorFragment(), "MindOrks")
        adapter.addFragment(AuthorFragment(), "MindOrks")
        adapter.addFragment(AuthorFragment(), "MindOrks")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


    private fun showModalBottomSheet() {
        val modalBottomSheet = BottomSheetDialogFragment()
        modalBottomSheet.show(supportFragmentManager, "")
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                showModalBottomSheet()
            }
        }
        return true
    }
}