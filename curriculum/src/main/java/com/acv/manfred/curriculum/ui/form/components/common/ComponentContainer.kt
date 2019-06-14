package com.acv.manfred.curriculum.ui.form.components.common

import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.acv.manfred.curriculum.presentation.form.component.common.Component
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentModel
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentResponse
import com.acv.manfred.curriculum.presentation.form.component.common.ObserveComponent
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.ByDefault

interface ComponentContainer<A : ComponentModel, B : ByDefault, C : ComponentResponse, D : Component<A, B, C>> {
    val adapter: ComponentAdapter<A>
    val container: ViewGroup

    fun createComponent(): Component<A, B, C>

    fun createAdapter(
        observable: ObserveComponent<A> = observable(),
        items: MutableList<A> = mutableListOf()
    ): ComponentAdapter<A> =
        ComponentAdapter(observable, items)

    fun List<A>.swap(compare: (A, A) -> Boolean = { a, b -> a.id.id == b.id.id }) {
        adapter.swap(this, compare)
    }

    private fun observable(): ObserveComponent<A> = object : ObserveComponent<A> {
        override fun remove(position: Int) {
            Log.e("remove", "$position")
            container.removeViewAt(position)
        }

        override fun insert(m: A) {
            Log.e("insert", "${m.id}")
            container.addView(createComponent().renderType(m) as View)
        }

        override fun moved(fromPosition: Int, toPosition: Int) {
            Log.e("moved", "from $fromPosition - to $toPosition")
            container.move(fromPosition, toPosition)
        }

        override fun change(position: Int, m: A) {
            Log.e("change", """$position , ${m}""")
            getItem(position).renderType(m)
        }
    }

    private fun getItem(position: Int): D =
        container.getChildAt(position) as D
}

fun <A> MutableList<A>.move(from: Int, to: Int) {
    if (from < to) moveUp(from, to) else moveDown(from, to)
}

fun <A> MutableList<A>.moveUp(from: Int, to: Int) {
    add(to, this[from])
    removeAt(from)
}

fun <A> MutableList<A>.moveDown(from: Int, to: Int) {
    add(to, this[from])
    removeAt(from + 1)
}

fun ViewGroup.move(from: Int, to: Int) {
    if (from < to) moveUp(from, to) else moveDown(from, to)
}

fun ViewGroup.moveUp(from: Int, to: Int) {
    val a = getChildAt(from)
    removeViewAt(from)
    addView(a, to - 1)
}

fun ViewGroup.moveDown(from: Int, to: Int) {
    val a = getChildAt(from)
    removeViewAt(from)
    addView(a, to)
}