package com.acv.manfred.curriculum.ui.form.components.common

interface ObserveComponent<M> {
    fun remove(position: Int)
    fun insert(m: M)
    fun moved(fromPosition: Int, toPosition: Int)
    fun change(position: Int, m: M)
}