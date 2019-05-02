package com.acv.manfred.curriculum.ui.form

import android.view.MenuItem
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.Actionable
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance
import com.acv.manfred.curriculum.ui.common.fragment.createFragment
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import com.acv.uikit.onClick
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : BaseActivity(), Launcher, Actionable {
    override val fb: FloatingActionButton get() = fbAction
    override val baseActivity: BaseActivity get() = this

    override fun getLayout(): Int = R.layout.activity_form

    override fun create(savedInstance: SavedInstance) {
        setSupportActionBar(bottomApp)
        createFragment<AuthorFragment>().load()

        fbAction onClick { Snackbar.make(snackBarContainer, "sadfsad", Snackbar.LENGTH_SHORT).show() }
    }

    private fun showModalBottomSheet() {
        val modalBottomSheet = BottomSheetDialogFragment()
        modalBottomSheet.show(supportFragmentManager, "")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> showModalBottomSheet()
        }
        return true
    }
}