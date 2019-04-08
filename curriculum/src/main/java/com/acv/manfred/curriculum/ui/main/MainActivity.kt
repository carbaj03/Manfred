package com.acv.manfred.curriculum.ui.main

import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.SavedInstance

class MainActivity : BaseActivity(), NavigatorAc {
    override val baseActivity: BaseActivity = this

    override fun getLayout(): Int = R.layout.main_activity

    override fun create(savedInstance: SavedInstance) {
//        loadForm()
        goToForm()
    }


}
