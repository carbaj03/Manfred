package com.acv.uikit.renderer

interface AdapteeCollection<T> {
    fun size(): Int
    operator fun get(index: Int): T
    fun add(element: T): Boolean
    fun remove(element: T): Boolean
    fun addAll(elements: Collection<T>): Boolean
    fun removeAll(elements: Collection<T>): Boolean
    fun clear()
}