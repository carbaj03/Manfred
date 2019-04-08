package com.acv.uikit.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.acv.uikit.adapterModel.BaseViewHolder
import com.fintonic.uikit.adapter.Visitable


abstract class BaseViewHolder<in M : Visitable>(
        view: View
) : RecyclerView.ViewHolder(view) {
    abstract fun bindTo(model: M)
}

abstract class ViewHolderWithParent<M: Visitable, in N : M>(
        view: View
) : BaseViewHolder<M>(view) {
    override fun bindTo(model: M) =
            itemView.view(model as N)

    abstract fun View.view(model: N)
}

abstract class ViewHolder<in M: Visitable>(
        view: View
) : BaseViewHolder<M>(view) {
    override fun bindTo(model: M) =
            itemView.view(model)

    abstract fun View.view(model: M)
}
