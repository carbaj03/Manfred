package com.acv.manfred.curriculum.ui.common.arch

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

interface Observable<A : BaseViewModel> {
    val model: A

    fun
            model(f: A.() -> Unit): Unit =
        model.run { f() }

    infix fun <M, T : M> LifecycleOwner.observe(f: A.() -> LiveData<T>): Obs2<T> =
        { o: (T) -> Unit -> f(model).observe(this, Observer { o(it!!) }) }
}