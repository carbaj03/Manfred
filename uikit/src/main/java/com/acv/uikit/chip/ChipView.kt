package com.acv.uikit.chip

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.acv.uikit.R
import com.acv.uikit.adapterModel.CGAdapter
import com.acv.uikit.common.Errorable
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ChipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ChipGroup(context, attrs, defStyleAttr), Errorable {
    private var adapter: CGAdapter = CGAdapter(observable(), mutableListOf())

    fun swap(newItems: List<ChipModel>, compare: (ChipModel, ChipModel) -> Boolean) {
        adapter.swap(newItems, compare)
    }

    private fun getChip(position: Int): Chip =
        getChildAt(position) as Chip

    private fun observable(): ObserveChip<ChipModel> = object : ObserveChip<ChipModel> {
        override fun remove(position: Int) {
            removeViewAt(position)
        }

        override fun insert(m: ChipModel) {
            addView(Chip(context, null, R.style.Widget_MaterialComponents_Chip_Entry).config(m))
        }

        override fun moved(fromPosition: Int, toPosition: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun change(position: Int, m: ChipModel) {
            getChip(position).config(m)
        }

        fun Chip.config(m: ChipModel): Chip {
            text = m.title

//            chipIcon = ContextCompat.getDrawable(context, R.drawable.ic_add)

            m.checable.map { c ->
                isChecked = c.isSelected
                isCheckable = true
                setOnClickListener { c.f(m) }
            }

            m.closelable.map { c ->
                isCloseIconVisible = true
                setOnCloseIconClickListener { c.f(m) }
            }
            return this
        }
    }

    override fun errorK(value: String) {

    }
}