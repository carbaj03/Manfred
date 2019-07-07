package com.acv.uikit.adapterModel

interface ObserveComponent<A> {
    fun remove(position: Int)
    fun insert(a: A)
    fun moved(fromPosition: Int, toPosition: Int)
    fun change(position: Int, a: A)
}