package com.acv.manfred.curriculum.data.gateway.datasource.local.db

import arrow.core.Either
import arrow.core.left
import com.acv.manfred.curriculum.domain.model.BaseError
import com.acv.manfred.curriculum.domain.model.NotFoundError

interface ModuleOps {
    suspend fun <A> either(f: suspend () -> Either<BaseError, A>): Either<BaseError, A> =
        try {
            f()
//        } catch (retrofitError: JsonParseException) {
//            ApiError(retrofitError.message.toOption()).left()
        } catch (t: Throwable) {
            NotFoundError.left()
        }

}

