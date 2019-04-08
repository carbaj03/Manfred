package com.acv.uikit.adapterRender

import androidx.recyclerview.widget.DiffUtil

fun <M> autoNotify(old: List<M>, new: List<M>, compare: (M, M) -> Boolean): DiffUtil.DiffResult =
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    compare(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize() =
                    old.size

            override fun getNewListSize() =
                    new.size
        })