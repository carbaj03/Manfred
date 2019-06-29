package com.acv.uikit.chip

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import arrow.core.None
import arrow.core.some
import com.acv.uikit.R
import com.acv.uikit.common.Errorable
import com.acv.uikit.common.click
import com.acv.uikit.dialog.DateDialog
import com.acv.uikit.dialog.FintonicDialogDateFragment
import kotlinx.android.synthetic.main.chips_social_view.view.*

class ChipsSocialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), Errorable, DateDialog {

    private var chips: List<ChipModel> = listOf()

    var state: Boolean = true

    override fun errorK(value: String) {
//        error = value
    }

    fun swap(newItems: List<ChipModel>, compare: (ChipModel, ChipModel) -> Boolean) {
        chipView.swap(newItems, compare)
    }

    init {
        inflate(context, R.layout.chips_social_view, this)
        orientation = VERTICAL

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ChipsSocialView, 0, 0)

        try {
//            text = a.getString(R.styleable.ChipHeaderAction_text) ?: ""
//            error = a.getString(R.styleable.ChipHeaderAction_error)
//            textAction = a.getString(R.styleable.ChipHeaderAction_textAction) ?: ""
//            icon = a.getDrawable(R.styleable.ChipHeaderAction_icon).some()
        } finally {
            a.recycle()
        }

//        rvOptions.run {
//            layoutManager = LinearLayoutManager(context)
//            addItemDecoration(DividerItemDecoration(context, VERTICAL))
//            isNestedScrollingEnabled = true
//            adapter = rvadapterModel
//            itemAnimator = DefaultItemAnimator()
//        }
    }

    fun action(activity: FragmentManager): Unit =
        btnAction click { createDialog().show(activity, "") }
//            if (state) {
//                tieInput.visible()
//                chipView.invisible()
//                state = false
//            } else {
//                tieInput.invisible()
//                chipView.visible()
//                state = true
//            }


    private fun createDialog(): FintonicDialogDateFragment =
        dateDialog {
            title = "Link Input"
            accept {
                listener {
                    chips = chips.plus(ChipModel(text, text, None, Closelable {
                        chips = chips.minus(it)
                        swap(chips) { a, b -> a.id == b.id }
                    }.some()))
                    swap(chips) { a, b -> a.id == b.id }
                    dismiss()
                }
            }
            cancel {
                listener { dismiss() }
            }
        }

}