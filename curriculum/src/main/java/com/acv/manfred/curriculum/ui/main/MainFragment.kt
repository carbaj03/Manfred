package com.acv.manfred.curriculum.ui.main

import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.fragment.viewModelProviders

class MainFragment : BaseFragment() {
    private val model by lazy { viewModelProviders<MainViewModel>() }

    override fun getLayout(): Int = R.layout.main_fragment

    override fun onCreate() {}
}


