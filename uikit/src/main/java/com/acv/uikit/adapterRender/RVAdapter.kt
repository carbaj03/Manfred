package com.acv.uikit.adapterRender

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acv.uikit.adapter.POSITION
import com.acv.uikit.common.inflate
import java.util.*

typealias Layout = Int

open class RVAdapter<M : Any>(
    private val l: (M, View) -> BaseViewHolder<M>,
    private val items: MutableList<M> = mutableListOf(),
    val f: HashMap<Class<out M>, Layout>
) : RecyclerView.Adapter<BaseViewHolder<M>>(), Adapter<M> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Layout): BaseViewHolder<M> =
        l(getKeysByValue(f, viewType)!!.newInstance(), parent.inflate(viewType))

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int): Unit =
        holder.bindTo(items[position])

    override fun getItemViewType(position: Int): Int {
        //Video -> R.layout.video
        return f[items[position]::class.java] ?: 0
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


fun <T, E> getKeysByValue(map: Map<T, E>, value: E): T? {
    for ((key, value1) in map) {
        if (Objects.equals(value, value1)) {
            return key
        }
    }
    return null
}