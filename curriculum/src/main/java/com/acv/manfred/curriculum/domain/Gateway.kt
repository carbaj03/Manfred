package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.*

typealias GatewayIO = CvGateway<ForIO>
typealias QuestionnaireGatewayIO = QuestionnaireGateway<ForIO>

typealias ResultK<A> = Either<BaseError, A>

interface CvGateway<F> {
    fun GetCvDto.get(): Kind<F, ResultK<Example>>
    fun RolesDto.get(): Kind<F, ResultK<List<RoleProfile>>>
    fun ProficiencyDto.get(): Kind<F, ResultK<List<Proficiency>>>
}

interface QuestionnaireGateway<F> {
    fun QuestionnaireDto.save() : Kind<F, ResultK<List<Questionnaire>>>
    fun AddQuestionnaireDto.add() : Kind<F, ResultK<List<Questionnaire>>>
    fun RemoveQuestionnaireDto.remove() : Kind<F, ResultK<List<Questionnaire>>>
    fun GetQuestionnaireDto.all() : Kind<F, ResultK<List<Questionnaire>>>
}

interface MiscEducationGateway<F> {
    fun MiscEducationDto.save() : Kind<F, ResultK<List<MiscEducation>>>
    fun AddMiscEducationDto.add() : Kind<F, ResultK<List<MiscEducation>>>
    fun RemoveMiscEducationDto.remove() : Kind<F, ResultK<List<MiscEducation>>>
    fun GetMiscEducationDto.all() : Kind<F, ResultK<List<MiscEducation>>>
}