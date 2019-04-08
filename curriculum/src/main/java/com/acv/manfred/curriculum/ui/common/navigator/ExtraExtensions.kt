package com.acv.manfred.curriculum.ui.common.navigator

import android.os.Bundle
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.fragment.BaseFragment
import com.acv.manfred.curriculum.ui.common.navigator.Extra.Companion.EXTRA
import java.io.Serializable

interface ExtraExtensions {
    fun <A> Extra<A>.extract(default: () -> A): A =
        fold(default, { it })

    fun <A> createExtra(extra: A): Bundle =
        Bundle().apply { putSerializable(Extra.EXTRA, Extra(extra)) }

    fun none(): Extra.None = Extra.None
}

interface ExtraExtensionsFr : ExtraExtensions {
    fun <A> BaseFragment.extra(default: () -> A): Lazy<A> =
        lazy { (getExtra<A>().extract(default)) }

    private fun <A> BaseFragment.getExtra(): Extra<A> =
        arguments?.getSerializable(EXTRA)!! as Extra<A>
}

interface ExtraExtensionsAc : ExtraExtensions {
    fun <A> BaseActivity.extra(default: () -> A): Lazy<A> =
        lazy { (getExtra<A>().extract(default)) }

    private fun <A> BaseActivity.getExtra(): Extra<A> =
        intent?.getSerializableExtra(EXTRA)!! as Extra<A>
}

sealed class Extra<out A> : Serializable {
    companion object {
        operator fun <A> invoke(a: A): Extra<A> =
            Some(a)

        operator fun invoke(): Extra<Nothing> =
            None

        const val EXTRA = "EXTRA"
    }

    fun toBundle(): Bundle =
        Bundle().apply { putSerializable(Extra.EXTRA, this@Extra) }

    inline fun <R> fold(ifEmpty: () -> R, ifSome: (A) -> R): R =
        when (this) {
            is None -> ifEmpty()
            is Some<A> -> ifSome(t)
        }

    inline fun <B> map(crossinline f: (A) -> B): Extra<B> =
        fold({ None }, { a -> Some(f(a)) })

    data class Some<T>(val t: T) : Extra<T>()
    object None : Extra<Nothing>()
}
