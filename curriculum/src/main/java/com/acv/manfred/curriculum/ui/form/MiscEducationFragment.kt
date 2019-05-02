package com.acv.manfred.curriculum.ui.form

import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment

class MiscEducationFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.view_misc_education

    override fun onCreate() {
        compatActivity.fab {
            show()
            setImageResource(R.drawable.ic_favorite)
        }
    }

}


