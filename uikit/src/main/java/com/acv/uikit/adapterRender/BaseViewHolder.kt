package com.acv.uikit.adapterRender

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in M>(
    val layout: Int,
    view: View
) : RecyclerView.ViewHolder(view), LayoutContainer {
    override val containerView: View? get() = itemView
    abstract fun bindTo(model: M)
}

abstract class ViewHolderWithParent<M, N : M>(
    layout: Int,
    view: View
) : BaseViewHolder<M>(layout, view) {
    override fun bindTo(model: M): Unit = (model as N).view()
    abstract fun N.view()
}

abstract class ViewHolder<in M>(
    layout: Int,
    view: View
) : BaseViewHolder<M>(layout, view) {
    override fun bindTo(model: M): Unit = model.view()
    abstract fun M.view()
}
