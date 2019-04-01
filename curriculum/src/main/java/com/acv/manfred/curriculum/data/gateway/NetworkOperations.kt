package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.core.left
import arrow.core.right
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.gateway.datasource.ApiModule
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.Result

interface NetworkOperations<F> : Async<F> {
    val network: ApiModule

    fun GetCvDto.requestCv(): Kind<F, Result<Example>> =
            call(network::requestGet)

    fun <A, R> A.call(a: Request<A, R>): Kind<F, R> =
            async { callback ->
                a(this, { err -> callback(err.left()) }, { value -> callback(value.right()) })
            }
}

typealias Request <A, R> = (A, (Throwable) -> Unit, (R) -> Unit) -> Unit