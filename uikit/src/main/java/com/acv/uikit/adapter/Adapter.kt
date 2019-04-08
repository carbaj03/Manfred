package com.acv.uikit.adapter

interface Adapter<M> {
    fun swap(newItems: List<M>, compare: (M, M) -> Boolean = { x, y -> x == y })
    fun set(position: POSITION, item: M)
}

typealias ViewType = Int
typealias POSITION = Int

