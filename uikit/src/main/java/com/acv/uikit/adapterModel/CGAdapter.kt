package com.acv.uikit.adapterModel

import androidx.recyclerview.widget.ListUpdateCallback
import com.acv.uikit.chip.ChipModel
import com.acv.uikit.chip.ObserveChip

class CGAdapter(
    private var observable: ObserveChip<ChipModel>,
    private var items: MutableList<ChipModel>
) : Adapter<ChipModel>, ListUpdateCallback {
    override fun set(position: POSITION, item: ChipModel) {
        items[position] = item
    }

    override fun swap(newItems: List<ChipModel>, compare: (ChipModel, ChipModel) -> Boolean) {
        val diffResult = autoNotify(items, newItems, compare)
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
        for (a in 0 until count)
            observable.insert(items[position + a])
    }

    override fun onRemoved(position: Int, count: Int) {
        for (a in (count - 1)..0)
            observable.remove(position)
    }
}