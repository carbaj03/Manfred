package com.acv.manfred.curriculum.ui.main

import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.createIntent
import com.acv.manfred.curriculum.ui.common.fragment.createFragment
import com.acv.manfred.curriculum.ui.common.navigator.ExtraExtensionsAc
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import com.acv.manfred.curriculum.ui.form.FormActivity

interface NavigatorAc : Launcher, ExtraExtensionsAc {
    fun BaseActivity.loadForm(): Unit =
        createFragment<MainFragment>().load()

    fun BaseActivity.goToForm(): Unit =
        createIntent<FormActivity>().start()
}