package com.acv.manfred.curriculum.ui.common.arch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.acv.manfredcv.presentation.common.activity.BaseActivity
import com.acv.manfredcv.presentation.common.fragment.BaseFragment
import com.acv.manfredcv.presentation.common.fragment.compatActivity


typealias Obs<T> = ((T) -> Unit)
typealias Obs2<T> = (Obs<T>) -> Unit

inline fun <reified T : ViewModel> BaseFragment.viewModelProviders(factory: ViewModelProvider.Factory = EmptyViewModelFactory): T =
        compatActivity.viewModelProviders(factory)

inline fun <reified T : ViewModel> BaseActivity.viewModelProviders(factory: ViewModelProvider.Factory = EmptyViewModelFactory): T =
        if (factory !== EmptyViewModelFactory)
            ViewModelProviders.of(this as FragmentActivity, factory).get(T::class.java)
        else
            ViewModelProviders.of(this as FragmentActivity).get(T::class.java)

infix fun <M, T : M> Fragment.observe(f: () -> LiveData<T>): Obs2<T> =
        { o: (T) -> Unit -> f().observe(this as LifecycleOwner, Observer { o(it!!) }) }

infix fun <T> Obs2<T>.map(f: Obs<T>): Obs2<T> =
        apply { this{ f(it) } }
