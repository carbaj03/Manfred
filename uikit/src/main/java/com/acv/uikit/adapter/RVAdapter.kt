package com.acv.uikit.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acv.uikit.adapterRender.Adapter
import com.acv.uikit.common.inflate
import com.acv.uikit.adapterRender.BaseViewHolder
import com.fintonic.uikit.adapter.Visitable

open class RVAdapter<M : Visitable>(
        private val factory: (ViewType) -> (View) -> BaseViewHolder<M>
) : RecyclerView.Adapter<BaseViewHolder<M>>(), Adapter<M> {

    private var items: MutableList<M> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: ViewType): BaseViewHolder<M> =
            factory(viewType)(parent.inflate(viewType))

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int): Unit =
            holder.bindTo(items[position])

    override fun getItemViewType(position: Int): Int =
            items[position].layout

    override fun getItemCount(): Int =
            items.size

    override fun set(position: POSITION, item: M) {
        items[position] = item
        notifyItemChanged(position)
    }

    override fun swap(newItems: List<M>, compare: (M, M) -> Boolean) {
        val diffResult = com.acv.uikit.adapterRender.autoNotify(items, newItems, compare)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}