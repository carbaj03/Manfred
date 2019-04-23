package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.model.*

typealias GatewayIO = CvGateway<ForIO>

typealias Result<A> = Either<BaseError, A>

interface CvGateway<F> {
    fun GetCvDto.get(): Kind<F, Result<Example>>
    fun RolesDto.get(): Kind<F, Result<List<RoleProfile>>>
    fun ProficiencyDto.get(): Kind<F, Result<List<Proficiency>>>

    fun QuestionnaireDto.save() : Kind<F, Result<List<Questionnaire>>>
    fun GetQuestionnaireDto.all() : Kind<F, Result<List<Questionnaire>>>
}

