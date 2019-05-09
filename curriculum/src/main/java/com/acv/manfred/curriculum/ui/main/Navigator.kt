package com.acv.manfred.curriculum.ui.main

import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.createIntent
import com.acv.manfred.curriculum.ui.common.navigator.ExtraExtensionsAc
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import com.acv.manfred.curriculum.ui.form.FormActivity
import com.acv.manfred.curriculum.ui.form.navigation.Navigation
import com.acv.manfred.curriculum.ui.form.navigation.createFragment

interface NavigatorAc : Launcher, ExtraExtensionsAc, Navigation {
    fun BaseActivity.loadForm(): Unit =
        createFragment<MainFragment>().load()

    fun BaseActivity.goToForm(): Unit =
        createIntent<FormActivity>().start()
}