package com.acv.manfred.curriculum.ui.form.navigation

import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.navigator.Extra

interface Navigation

inline fun <reified A : BaseFragment> Navigation.createFragment(args: Extra<*> = Extra.None): A =
    A::class.java.newInstance().apply { arguments = args.toBundle() }