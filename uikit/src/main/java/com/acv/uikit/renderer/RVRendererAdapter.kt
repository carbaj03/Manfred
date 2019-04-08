package com.acv.uikit.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acv.uikit.renderer.exception.NullRendererBuiltException


class RVRendererAdapter<T : Any>(
    private val rendererBuilder: RendererBuilder<T>,
//    val builder : (ViewGroup, LayoutInflater, Int) -> RendererViewHolder<T>?,
    private var collection: AdapteeCollection<T>
) : RecyclerView.Adapter<RendererViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RendererViewHolder<T> {
        rendererBuilder.withParent(parent)
        rendererBuilder.withLayoutInflater(LayoutInflater.from(parent.context))
        rendererBuilder.withViewType(viewType)
        return rendererBuilder.buildRendererViewHolder()
//        return  builder(parent, LayoutInflater.from(parent.context), viewType)
            ?: throw NullRendererBuiltException("RendererBuilder have to return a not null viewHolder")
    }

    override fun getItemCount(): Int =
        collection.size()

    override fun onBindViewHolder(holder: RendererViewHolder<T>, position: Int) {
        val content = getItem(position)
        val renderer: Renderer<T> = holder.renderer
            ?: throw NullRendererBuiltException("RendererBuilder have to return a not null renderer")
        renderer.content = content
        updateRendererExtraValues(content, renderer, position)
        renderer.render()
    }

    override fun getItemViewType(position: Int): Int {
        val content = getItem(position)
        return rendererBuilder.getItemViewType(content)
    }

    fun updateRendererExtraValues(content: T, renderer: Renderer<T>, position: Int) {

    }

    fun getItem(position: Int): T =
        collection[position]

    fun diffUpdate(newList: List<T>) {
        if (collection.size() == 0) {
            addAll(newList)
            notifyDataSetChanged()
        } else {
            val diffCallback = DiffCallback(collection, newList) { a, b -> a!! == b }
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            clear()
            addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    fun addAll(elements: Collection<T>): Boolean =
        collection.addAll(elements)

    fun clear() {
        collection.clear()
    }

}