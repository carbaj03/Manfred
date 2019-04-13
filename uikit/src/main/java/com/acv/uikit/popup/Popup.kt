package com.acv.uikit.popup

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.acv.uikit.R
import com.acv.uikit.adapterModel.AdapterModel
import com.acv.uikit.adapterModel.RVAdapter
import com.acv.uikit.adapterModel.ViewHolder
import com.acv.uikit.color.Color
import com.acv.uikit.common.Component
import com.acv.uikit.common.EditMode
import com.acv.uikit.common.Errorable
import kotlinx.android.synthetic.main.item_popup.*
import kotlinx.android.synthetic.main.view_popup_list.view.*


class Popup @JvmOverloads constructor(
    val context: Context,
    val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PopupWindow(context, attrs, defStyleAttr), Errorable, Component<PopupModel> {
    private val rvadapterModel by lazy { RVAdapter<PopupAdapter> { video, v -> PopupAdapterHolder(v) } }

    override fun errorK(value: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
//        height = LinearLayout.LayoutParams.MATCH_PARENT
//        width = LinearLayout.LayoutParams.MATCH_PARENT
        normalMode()
    }

    override fun render(model: PopupModel): Component<PopupModel> {
        rvadapterModel.swap(listOf(PopupAdapter("title1"), PopupAdapter("title1")))
        return this
    }

    fun normalMode() {
        val view = inflateView().apply {
            rvList.run {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
                isNestedScrollingEnabled = true
                adapter = rvadapterModel
                itemAnimator = DefaultItemAnimator()
            }
        }
        contentView = view

        if (Build.VERSION.SDK_INT >= 21) {
            elevation = 8f
        }

        isOutsideTouchable = true
        isFocusable = true
    }

    private fun inflateView() =
        LayoutInflater.from(context).inflate(R.layout.view_popup_list, null)

    fun show(view: View) {
        rvadapterModel.swap(listOf(PopupAdapter("title1"), PopupAdapter("title1")))
        showAsDropDown(view)
    }
}


data class PopupAdapter(
    var title: String = ""
) : AdapterModel(R.layout.item_popup)


class PopupAdapterHolder(
    view: View
) : ViewHolder<PopupAdapter>(view) {
    override fun PopupAdapter.view() {
        text.text = title
    }
}