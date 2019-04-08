package com.acv.uikit.adapterModel

import com.acv.uikit.adapter.POSITION

interface Adapter<M> {
    fun swap(newItems: List<M>, compare: (M, M) -> Boolean = { x, y -> x == y })
    fun set(position: POSITION, item: M)
}

typealias ViewType = Int
typealias POSITION = Int

