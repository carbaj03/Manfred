package com.acv.manfredcv.presentation.common.fragment

import androidx.core.content.ContextCompat
import com.acv.manfredcv.presentation.common.activity.*

val BaseFragment.compatActivity: BaseActivity
    get() = activity as BaseActivity

fun BaseFragment.configToolbar(newTitle: String) =
        compatActivity.configToolbar(newTitle)

fun BaseFragment.emptyTitle() =
        configToolbar("")

fun BaseFragment.showAlert(msg: String, duration: Duration = DefaultDuration) =
        compatActivity.showAlert(msg, duration)

fun BaseFragment.color(color: Int) =
        ContextCompat.getColor(context!!, color)