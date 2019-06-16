package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.core.toOption
import arrow.effects.ForIO
import arrow.effects.fix
import arrow.effects.typeclasses.Disposable
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.BaseError
import com.acv.manfred.curriculum.domain.model.UnknownServerError

typealias GatewayResult<A> = Kind<ForIO, A>

fun <A> GatewayResult<A>.executeWithError(error: (BaseError) -> Unit): Disposable =
        execute(error, {})

fun <A> GatewayResult<ResultK<A>>.executeResult(error: (BaseError) -> Unit = {}, success: (A) -> Unit): Disposable =
        execute(error, { result -> result.fold({ error(it) }, { success(it) }) })

fun <A> GatewayResult<A>.execute(error: (BaseError) -> Unit = {}, success: (A) -> Unit): Disposable =
        fix().unsafeRunAsyncCancellable { foldResult(it, error, success) }

private fun <A> foldResult(it: Either<Throwable, A>, fail: (BaseError) -> Unit, success: (A) -> Unit) =
        it.fold({ err -> fail(UnknownServerError(err.message.toOption())) }, { booking -> success(booking) })