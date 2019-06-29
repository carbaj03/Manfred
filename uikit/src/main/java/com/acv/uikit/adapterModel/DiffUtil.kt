package com.acv.uikit.adapterModel

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff

interface DiffUtil {

    fun <M> autoNotify(old: List<M>, new: List<M>, compare: (M, M) -> Boolean): DiffUtil.DiffResult =
        calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val a = compare(old[oldItemPosition], new[newItemPosition])
                Log.e("same", "$a")
                return a
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val a = old[oldItemPosition] == new[newItemPosition]
                Log.e("equal",
                      """${old[oldItemPosition]}
                          |${new[newItemPosition]}
                      """.trimMargin())
                return a
            }

            override fun getOldListSize() =
                old.size

            override fun getNewListSize() =
                new.size

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
               new[newItemPosition]

        })

    fun <M> autoNotify(old: M, new: M, compare: (M, M) -> Boolean): DiffUtil.DiffResult =

        calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                compare(old, new)

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old == new

            override fun getOldListSize(): Int =
                1

            override fun getNewListSize(): Int =
                1

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
                new
        })
}