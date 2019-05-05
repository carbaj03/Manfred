package com.acv.manfred.curriculum.ui.common.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        create(SavedInstance(savedInstanceState))
    }

    abstract fun create(savedInstance: SavedInstance)
    abstract fun getLayout(): Int

    fun <T : BaseFragment> T.load(container: Int = R.id.fragment_container): Unit =
        supportFragmentManager
            .beginTransaction()
            .replace(container, this)
            .commitNow()
}

inline fun <reified M> BaseActivity.createIntent(f: Intent.() -> Unit = {}): Intent =
    Intent(this, M::class.java).apply { f() }


data class SavedInstance(val a: Bundle) {
    companion object {
        operator fun invoke(a: Bundle?): SavedInstance =
            a?.let { SavedInstance(a) } ?: SavedInstance(Bundle())
    }
}


fun BaseActivity.fab(f: FloatingActionButton.() -> Unit) {
    when (this) {
        is Actionable -> config { f() }
        else -> Log.e("Error", "Is not actionable")
    }
}

fun BaseActivity.snack(msg: String) {
    when (this) {
        is Notificable -> notify(msg)
        else -> Log.e("Error", "Is not notificable")
    }
}

interface Actionable {
    val fb: FloatingActionButton

    fun config(f: FloatingActionButton.() -> Unit): Unit =
        f(fb)
}

interface Notificable {
    val cl: CoordinatorLayout

    fun notify(msg: String): Unit =
        Snackbar.make(cl, msg, Snackbar.LENGTH_SHORT).show()

}