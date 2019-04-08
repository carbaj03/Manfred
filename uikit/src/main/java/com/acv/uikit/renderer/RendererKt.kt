package com.acv.uikit.renderer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acv.uikit.renderer.exception.NotInflateViewException
import kotlinx.android.extensions.LayoutContainer


abstract class Renderer<T> (
    var content: T? = null
) : Cloneable , LayoutContainer {

    override val containerView: View? get() = rootView
    private lateinit var rootView: View

    fun onCreate(content: T?, layoutInflater: LayoutInflater, parent: ViewGroup) {
        this.content = content

        inflate(layoutInflater, parent)?.let {
            this.rootView = it
            it.tag = this
//            setUpView(it)
            hookListeners(it)
        } ?: throw NotInflateViewException("Renderer instances have to return a not null view in inflateView method")

    }

    protected abstract fun inflate(inflater: LayoutInflater, parent: ViewGroup): View?

//    protected abstract fun setUpView(rootView: View)

    protected abstract fun hookListeners(rootView: View)

    fun getRootView(): View = rootView

    protected fun getContext(): Context? {
        return getRootView().context
    }

    abstract fun render()

    internal fun copy(): Renderer<T> {
//        var copy: Renderer<T>? = null
//        try {
//            copy = this.clone() as Renderer<T>
//        } catch (e: CloneNotSupportedException) {
//            Log.e("Renderer", "All your renderers should be clonables.")
//        }

        return this.clone() as Renderer<T>
    }

}