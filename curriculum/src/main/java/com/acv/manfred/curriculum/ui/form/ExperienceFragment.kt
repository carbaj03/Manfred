package com.acv.manfred.curriculum.ui.form

import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.fab
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment

class ExperienceFragment : BaseFragment() {

    override fun getLayout(): Int = R.layout.view_experience

    override fun onCreate() {
        compatActivity.fab {
            show()
            setImageResource(R.drawable.ic_save)
        }
    }

}


