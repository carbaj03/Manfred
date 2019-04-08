package com.acv.manfred.curriculum.ui.common.activity

import android.widget.Toast

const val REQUEST_CODE = 0

fun BaseActivity.configToolbar(newTitle: String) {
    supportActionBar!!.run {
        setDisplayShowTitleEnabled(true)
        setDisplayHomeAsUpEnabled(true)
        title = newTitle
    }
}

fun BaseActivity.showAlert(msg: String, duration: Duration = DefaultDuration) =
        Toast.makeText(this, msg, duration.time).show()

data class Duration(val time: Int = Toast.LENGTH_SHORT)

val DefaultDuration = Duration()

