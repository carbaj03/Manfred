package com.acv.uikit.adapterModel

interface Adapter<M> {
    fun swap(newItems: List<M>, compare: (M, M) -> Boolean = { x, y -> x == y })
    fun set(position: POSITION, item: M)
}

interface FAdapter<M> {
    fun swap()
}

typealias POSITION = Int

