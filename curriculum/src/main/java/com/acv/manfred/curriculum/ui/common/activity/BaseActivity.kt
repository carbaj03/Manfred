package com.acv.manfred.curriculum.ui.common.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.arch.EmptyViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.Obs2
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment


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

inline fun <reified T : ViewModel> BaseActivity.viewModelProviders(factory: ViewModelProvider.Factory = EmptyViewModelFactory): T =
    viewModelProvider(factory).get(T::class.java)

fun BaseActivity.viewModelProvider(factory: ViewModelProvider.Factory): ViewModelProvider =
    if (factory !== EmptyViewModelFactory) ViewModelProviders.of(this, factory)
    else ViewModelProviders.of(this)

inline fun <reified M> BaseActivity.createIntent(f: Intent.() -> Unit = {}): Intent =
    Intent(this, M::class.java).apply { f() }


data class SavedInstance(val a: Bundle) {
    companion object {
        operator fun invoke(a: Bundle?): SavedInstance =
            a?.let { SavedInstance(a) } ?: SavedInstance(Bundle())
    }
}

//LiveDate
infix fun <M, T : M> BaseActivity.observe(f: () -> LiveData<T>): Obs2<T> =
    { o: (T) -> Unit -> f().observe(this as LifecycleOwner, Observer { o(it!!) }) }