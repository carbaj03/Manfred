package com.acv.uikit.adapterModel

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.ListUpdateCallback

interface Up<B : Updatable> {
    fun <A> notify(observable: ObserveComponent<A>, items: List<A>): B
}

interface AndroidUp : Up<AndroidUpdatable> {
    override fun <A> notify(observable: ObserveComponent<A>, items: List<A>): AndroidUpdatable=
        AndroidUpdatable(
            object : ListUpdateCallback {
                override fun onChanged(position: Int, count: Int, payload: Any?) {
                    observable.change(position, items[position])
                }

                override fun onMoved(fromPosition: Int, toPosition: Int) {
                    observable.moved(fromPosition, toPosition)
                }

                override fun onInserted(position: Int, count: Int) {
                    for (a in 0 until count) {
                        observable.insert(items[position + a])
                    }
                }

                override fun onRemoved(position: Int, count: Int) {
                    for (a in (count - 1)..0) {
                        observable.remove(position)
                    }
                }
            }
        )
}




interface Diff<B : Differ> {
    fun <A> notify(old: List<A>, new: List<A>, compare: (A, A) -> Boolean): B
}

interface AndroidDiff : Diff<AndroidDiffer> {
    override fun <A> notify(old: List<A>, new: List<A>, compare: (A, A) -> Boolean): AndroidDiffer =
        AndroidDiffer(
            calculateDiff(
                object : DiffUtil.Callback() {
                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val a = compare(old[oldItemPosition], new[newItemPosition])
                        Log.e("same", "$a")
                        return a
                    }

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val a = old[oldItemPosition] == new[newItemPosition]
                        Log.e(
                            "equal",
                            """${old[oldItemPosition]}
                          |${new[newItemPosition]}
                      """.trimMargin()
                        )
                        return a
                    }

                    override fun getOldListSize() =
                        old.size

                    override fun getNewListSize() =
                        new.size

                    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
                        new[newItemPosition]
                })
        )
}


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
                Log.e(
                    "equal",
                    """${old[oldItemPosition]}
                          |${new[newItemPosition]}
                      """.trimMargin()
                )
                return a
            }

            override fun getOldListSize() =
                old.size

            override fun getNewListSize() =
                new.size

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
                new[newItemPosition]

        })

    suspend fun <B> asyncNotify(old: List<B>, new: List<B>, compare: (B, B) -> Boolean): AndroidDiffer =
        AndroidDiffer(
            calculateDiff(
                object : DiffUtil.Callback() {
                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val a = compare(old[oldItemPosition], new[newItemPosition])
                        Log.e("same", "$a")
                        return a
                    }

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        val a = old[oldItemPosition] == new[newItemPosition]
                        Log.e(
                            "equal",
                            """${old[oldItemPosition]}
                          |${new[newItemPosition]}
                      """.trimMargin()
                        )
                        return a
                    }

                    override fun getOldListSize() =
                        old.size

                    override fun getNewListSize() =
                        new.size

                    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
                        new[newItemPosition]

                })
        )

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