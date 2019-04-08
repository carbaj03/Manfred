package com.acv.uikit.renderer


open class ListAdapteeCollection<T>(
    private val list: MutableList<T> = mutableListOf()
) : AdapteeCollection<T> {
    override fun size(): Int = list.size

    override fun get(index: Int): T = list[index]

    override fun add(element: T): Boolean = list.add(element)

    override fun remove(element: T): Boolean = list.remove(element)

    override fun addAll(elements: Collection<T>): Boolean = list.addAll(elements)

    override fun removeAll(elements: Collection<T>): Boolean =
        list.removeAll(elements)

    override fun clear() {
        list.clear()
    }
}