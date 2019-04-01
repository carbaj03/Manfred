package com.acv.manfred.curriculum.data.gateway

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.CvGateway
import com.acv.manfred.curriculum.domain.GetCvDto
import com.acv.manfred.curriculum.domain.Result
import kotlin.coroutines.CoroutineContext

interface RequestOperations<F> : Async<F>, NetworkOperations<F>, DomainMapper<F>, CvGateway<F> {
    val ctx: CoroutineContext
    val main: CoroutineContext

    override fun GetCvDto.get(): Kind<F, Result<Example>> =
            defer(ctx) {
                requestCv().toDomainFromNetworkWithError()
            }.continueOn(main)
}