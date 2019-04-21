package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.k
import arrow.effects.ForIO
import arrow.effects.IO
import arrow.effects.extensions.io.applicativeError.handleError
import arrow.effects.fix
import arrow.effects.typeclasses.Disposable

typealias GatewayResult<A> = Kind<ForIO, A>

fun <A> GatewayResult<A>.executeWithError(error: (String) -> Unit): Disposable =
        execute(error, {})

fun <A> GatewayResult<Result<A>>.executeResult(error: (Error) -> Unit = {}, success: (A) -> Unit): Disposable =
        execute({}, { result -> result.fold({ error(it) }, { success(it) }) })

fun <A> GatewayResult<A>.execute(error: (String) -> Unit = {}, success: (A) -> Unit): Disposable =
        fix().unsafeRunAsyncCancellable {
            it.fold(
                    { err -> error(err.message!!) },
                    { booking -> success(booking) })
        }

fun <A> GatewayResult<A>.success(f: (A) -> Unit): IO<Unit> =
        fix().map { f(it) }

fun <A> GatewayResult<A>.error(f: (String) -> Unit): IO<Any?> =
        fix().handleError { f(it.message!!) }