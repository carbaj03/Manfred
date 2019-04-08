package com.acv.uikit.renderer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.acv.uikit.renderer.exception.*

class RendererBuilder<T : Any>(
    private var parent: ViewGroup? = null,
    private var layoutInflater: LayoutInflater? = null,
    private var viewType: Int = -1,
    private var prototypes: MutableList<Renderer<T>> = mutableListOf()
) {

    private var binding: MutableMap<Class<T>, Class<Renderer<T>>> = hashMapOf()

    fun withParent(parent: ViewGroup): RendererBuilder<T> =
        apply { this.parent = parent }

    fun withLayoutInflater(layoutInflater: LayoutInflater): RendererBuilder<T> =
        apply { this.layoutInflater = layoutInflater }

    fun withViewType(viewType: Int): RendererBuilder<T> =
        apply { this.viewType = viewType }

    fun buildRendererViewHolder(): RendererViewHolder<T>? {
        validateAttributesToCreateANewRendererViewHolder()

        val renderer: Renderer<T> = getPrototypeByIndex(viewType).copy()
        renderer.onCreate(null, layoutInflater!!, parent!!)
        return RendererViewHolder(renderer)
    }

    private fun validateAttributesToCreateANewRendererViewHolder() {
        if (viewType == -1) {
            throw NullContentException("RendererBuilder needs a view type to create a RendererViewHolder")
        }
        if (layoutInflater == null) {
            throw NullLayoutInflaterException("RendererBuilder needs a LayoutInflater to create a RendererViewHolder")
        }
        if (parent == null) {
            throw NullParentException("RendererBuilder needs a parent to create a RendererViewHolder")
        }
    }

    private fun getPrototypeByIndex(viewType: Int): Renderer<T> {
        var prototypeSelected: Renderer<T>? = null
        var i = 0
        for (prototype in prototypes) {
            if (i == viewType) {
                prototypeSelected = prototype
            }
            i++
        }
        return prototypeSelected!!
    }

    fun withPrototype(renderer: Renderer<T>?): RendererBuilder<T> {
        if (renderer == null) {
            throw NeedsPrototypesException("RendererBuilder can't use a null Renderer<T> instance as prototype")
        }
        this.prototypes.add(renderer)
        return this
    }

    fun bind(clazz: Class<T>?, prototypeClass: Class<Renderer<T>>?): RendererBuilder<T> {
        if (clazz == null || prototypeClass == null) {
            throw IllegalArgumentException("The binding RecyclerView binding can't be configured using null instances")
        }
        binding.put(clazz, prototypeClass)
        return this
    }


    fun getItemViewType(content: T): Int {
        val prototypeClass = getPrototypeClass(content)!!
        validatePrototypeClass(prototypeClass)
        return getItemViewType(prototypeClass)
    }

    private fun validatePrototypeClass(prototypeClass: Class<*>?) {
        if (prototypeClass == null) {
            throw NullPrototypeClassException(
                "Your getPrototypeClass method implementation can't return a null class"
            )
        }
    }

    fun getPrototypeClass(c: T): Class<*>? {
        return if (prototypes.size == 1) {
            prototypes[0]::class.java
        } else {
            val a : T = c
            binding[a::class.java]
        }
    }

    private fun getItemViewType(prototypeClass: Class<*>): Int {
        var itemViewType = -1
        for (renderer in prototypes) {
            if (renderer::class.java == prototypeClass) {
                itemViewType = getPrototypeIndex(renderer)
                break
            }
        }
        if (itemViewType == -1) {
            throw PrototypeNotFoundException("Review your RendererBuilder implementation, you are returning one" + " prototype class not found in prototypes collection")
        }
        return itemViewType
    }

    private fun getPrototypeIndex(renderer: Renderer<T>): Int {
        var index = 0
        for (prototype in prototypes) {
            if (prototype::class.java == renderer::class.java) {
                break
            }
            index++
        }
        return index
    }

}