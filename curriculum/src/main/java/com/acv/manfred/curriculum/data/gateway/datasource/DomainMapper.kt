package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.data.example.Example
import com.acv.manfred.curriculum.domain.Result


interface DomainMapper<F> : MonadError<F, Throwable> {

    fun Kind<F, Result<Example>>.toDomainFromNetworkWithError(): Kind<F, Result<Example>> =
        flatMap { catch { it.map { it.toDomain() } } }
}

