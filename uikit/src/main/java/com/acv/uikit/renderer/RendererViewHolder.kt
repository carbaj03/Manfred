package com.acv.uikit.renderer

import androidx.recyclerview.widget.RecyclerView

class RendererViewHolder<T>(
    val renderer: Renderer<T>?
) : RecyclerView.ViewHolder(renderer!!.getRootView()) {

}