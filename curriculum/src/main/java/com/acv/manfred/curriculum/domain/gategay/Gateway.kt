package com.acv.manfred.curriculum.domain.gategay

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.model.*

typealias GatewayIO = CvGateway<ForIO>

typealias ResultK<A> = Either<BaseError, A>
typealias ResultE<F, T> = Kind<F, ResultK<T>>