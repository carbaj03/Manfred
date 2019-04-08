package com.acv.manfred.curriculum.ui.common.navigator

import android.content.Context
import android.content.Intent
import com.acv.manfred.curriculum.ui.common.WithActivity

interface Launcher : WithActivity {
    fun Intent.start(code: Int): Unit =
        baseActivity.startActivityForResult(this, code)

    fun Intent.start(): Unit =
        baseActivity.startActivity(this)

    fun createIntentResult(f: Intent.() -> Unit = {}): Intent =
        Intent().apply { f() }
}

inline fun <reified M> Context.createIntent(f: Intent.() -> Unit = {}): Intent =
    Intent(this, M::class.java).apply { f() }