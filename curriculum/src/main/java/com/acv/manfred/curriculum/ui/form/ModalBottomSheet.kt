package com.acv.manfred.curriculum.ui.form

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.createIntent
import com.acv.manfred.curriculum.ui.common.fragment.createFragment
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.modal_bottom_sheet.*

class BottomSheetDialogFragment : AppCompatDialogFragment(), ModalNavigation {
    override val baseActivity: BaseActivity get() = activity as BaseActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.modal_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.itemId.navigate()
            dismiss()
//            // Bottom Navigation Drawer menu item clicks
//            when (menuItem.itemId) {
//                R.id.nav1 -> Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
//                R.id.nav2 -> Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
//                R.id.nav3 -> Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
//            }
            true
        }
    }
}

interface ModalNavigation : Launcher {
    override val baseActivity: BaseActivity

    fun Int.navigate(): Unit =
        when (this) {
            R.id.nav1 -> baseActivity.run { createFragment<AuthorFragment>().load() }
            R.id.nav2 -> baseActivity.run { createFragment<ExperienceFragment>().load() }
            R.id.nav3 -> baseActivity.run { createFragment<EducationFragment>().load() }
            else -> baseActivity.run { createFragment<IntroFragment>().load() }
        }
}