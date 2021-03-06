package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.fx.IO
import arrow.fx.typeclasses.Async

interface CallAsync<F> : Async<F> {
    fun <A, R> A.call(a: Request<A, R>): Kind<F, R> =
        async { callback ->
            a(this, { err -> callback(err.left()) }, { value -> callback(value.right()) })
        }

    fun <A, R> Request<A, R>.call(a: A): Kind<F, R> =
        async { callback ->
            this(a, { err -> callback(err.left()) }, { value -> callback(value.right()) })
        }
}

typealias Request <A, R> = (A, (Throwable) -> Unit, (R) -> Unit) -> Unit