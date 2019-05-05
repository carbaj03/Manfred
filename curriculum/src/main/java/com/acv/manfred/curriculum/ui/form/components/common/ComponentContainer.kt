package com.acv.manfred.curriculum.ui.form.components.common

import android.view.View
import android.view.ViewGroup

interface ComponentContainer<A : ComponentModel, B : Component<A>> {
    val adapter: ComponentAdapter<A>
    val container: ViewGroup

    fun createComponent(): Component<A>

    fun createAdapter(
        observable: ObserveComponent<A> = observable(),
        items: MutableList<A> = mutableListOf()
    ): ComponentAdapter<A> =
        ComponentAdapter(observable, items)

    fun List<A>.swap(compare: (A, A) -> Boolean = { a, b -> a.id == b.id }) {
        adapter.swap(this, compare)
    }

    private fun observable(): ObserveComponent<A> = object : ObserveComponent<A> {
        override fun remove(position: Int) {
            container.removeViewAt(position)
        }

        override fun insert(m: A) {
            container.addView(createComponent().renderType(m) as View)
        }

        override fun moved(fromPosition: Int, toPosition: Int) {
        }

        override fun change(position: Int, m: A) {
            getItem(position).renderType(m)
        }
    }

    private fun getItem(position: Int): B =
        container.getChildAt(position) as B
}