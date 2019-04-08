package com.acv.uikit.renderer

import androidx.recyclerview.widget.DiffUtil


class DiffCallback<T>(
    private val oldList: AdapteeCollection<T>,
    private val newList: List<T>,
    val compare: (T, T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size()
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem: T = oldList[oldItemPosition]
        val newItem: T = newList[newItemPosition]
        return compare(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]!! == newList[newItemPosition]
    }
}