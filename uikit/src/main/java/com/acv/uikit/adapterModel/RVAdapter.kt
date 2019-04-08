package com.acv.uikit.adapterModel

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acv.uikit.adapter.POSITION
import com.acv.uikit.common.inflate

typealias Layout = Int

abstract class AdapterModel(val layout: Layout)

open class RVAdapter<M : AdapterModel>(
    private val items: MutableList<M> = mutableListOf(),
    private val l: (M, View) -> BaseViewHolder<M>
) : RecyclerView.Adapter<BaseViewHolder<M>>(), Adapter<M> {
    private val a: HashMap<Layout, Class<out M>> = hashMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Layout): BaseViewHolder<M> =
        l(a[viewType]!!.newInstance(), parent.inflate(viewType))

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int): Unit =
        holder.bindTo(items[position])

    override fun getItemViewType(position: Int): Int {
        a[items[position].layout] = items[position]::class.java
        return items[position].layout
    }

    override fun getItemCount(): Int =
        items.size

    override fun set(position: POSITION, item: M) {
        items[position] = item
        notifyItemChanged(position)
    }

    override fun swap(newItems: List<M>, compare: (M, M) -> Boolean) {
        val diffResult = autoNotify(items, newItems, compare)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}