package com.acv.manfred.curriculum.ui.form

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.form.navigation.MainNavigation
import com.acv.manfred.curriculum.ui.form.navigation.ModalNavigation
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.modal_bottom_sheet.*

class BottomSheetDialogFragment : AppCompatDialogFragment(), ModalNavigation {
    override val baseActivity: BaseActivity get() = activity as BaseActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.modal_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            MainNavigation(menuItem.itemId).navigate()
            dismiss()
            true
        }
    }
}