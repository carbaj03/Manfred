package com.acv.uikit.chip


interface ObserveChip<M> {
    fun remove(position: Int)
    fun insert(m: M)
    fun moved(fromPosition: Int, toPosition: Int)
    fun change(position: Int, m: M)
}