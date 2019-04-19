package com.acv.manfred.curriculum.ui.form

import android.view.MenuItem
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance
import com.acv.manfred.curriculum.ui.common.fragment.createFragment
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : BaseActivity() , Launcher{
    override val baseActivity: BaseActivity get() = this

    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        setSupportActionBar(bottomApp)
        createFragment<AuthorFragment>().load()
    }

    private fun showModalBottomSheet() {
        val modalBottomSheet = BottomSheetDialogFragment()
        modalBottomSheet.show(supportFragmentManager, "")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> { showModalBottomSheet() }
        }
        return true
    }
}