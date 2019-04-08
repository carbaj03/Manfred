package com.acv.manfred.curriculum.ui.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import arrow.core.none
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.activity.viewModelProviders
import com.acv.manfred.curriculum.ui.common.arch.EmptyViewModelFactory
import com.acv.manfred.curriculum.ui.common.arch.Obs2
import com.acv.manfred.curriculum.ui.common.navigator.Extra
import com.acv.manfred.curriculum.ui.common.navigator.ExtraExtensionsFr

abstract class BaseFragment : Fragment(), ExtraExtensionsFr {
    val compatActivity: BaseActivity
        get() = activity as BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(getLayout(), container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreate()
    }

    protected abstract fun getLayout(): Int
    protected abstract fun onCreate()
}

//ViewModel
inline fun <reified T : ViewModel> BaseFragment.viewModelProviders(factory: ViewModelProvider.Factory = EmptyViewModelFactory): T =
    compatActivity.viewModelProviders(factory)

//Navigation
inline fun <reified A : BaseFragment> createFragment(args: Extra<*> = Extra.None): A =
    A::class.java.newInstance().apply { arguments = args.toBundle() }

//LiveDate
infix fun <M, T : M> BaseFragment.observe(f: () -> LiveData<T>): Obs2<T> =
    { o: (T) -> Unit -> f().observe(this as LifecycleOwner, Observer { o(it!!) }) }