package com.acv.manfred.curriculum.ui.form.components.common

import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.ListUpdateCallback
import com.acv.manfred.curriculum.presentation.form.component.common.ComponentModel
import com.acv.manfred.curriculum.presentation.form.component.common.ObserveComponent
import com.acv.uikit.adapterModel.Adapter
import com.acv.uikit.adapterModel.DiffUtil
import com.acv.uikit.adapterModel.POSITION

class ComponentAdapter<A : ComponentModel>(
    private var observable: ObserveComponent<A>,
    private var items: MutableList<A>
) : Adapter<A>, ListUpdateCallback, DiffUtil {
    override fun set(position: POSITION, item: A) {
        items[position] = item
    }

    override fun swap(newItems: List<A>, compare: (A, A) -> Boolean) {
        val diffResult: DiffResult = autoNotify(items, newItems, compare)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        observable.change(position, items[position])
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        observable.moved(fromPosition, toPosition)
    }

    override fun onInserted(position: Int, count: Int) {
        for (a in 0 until count){
            observable.insert(items[position + a])
        }
    }

    override fun onRemoved(position: Int, count: Int) {
        for (a in (count - 1)..0){
            observable.remove(position)
        }
    }
}