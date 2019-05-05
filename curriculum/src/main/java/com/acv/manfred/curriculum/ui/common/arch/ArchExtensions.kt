package com.acv.manfred.curriculum.ui.common.arch


typealias Obs<T> = (T.() -> Unit)
typealias Obs2<T> = (Obs<T>) -> Unit

infix fun <T> Obs2<T>.map(f: Obs<T>): Obs2<T> =
    apply { this{ f() } }