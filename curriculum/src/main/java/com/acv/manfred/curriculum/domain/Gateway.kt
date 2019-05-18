package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.*

typealias GatewayIO = CvGateway<ForIO>

typealias ResultK<A> = Either<BaseError, A>
typealias ResultE<F, T> = Kind<F, ResultK<T>>

interface CvGateway<F> {
    fun GetCvDto.get(): ResultE<F, Example>
    fun RolesDto.get(): ResultE<F, List<RoleProfile>>
}

interface QuestionnaireGateway<F> {
    fun QuestionnaireDto.save(): ResultE<F, List<Questionnaire>>
    fun AddQuestionnaireDto.add(): ResultE<F, List<Questionnaire>>
    fun RemoveQuestionnaireDto.remove(): ResultE<F, List<Questionnaire>>
    fun GetQuestionnaireDto.all(): ResultE<F, List<Questionnaire>>
}

interface MiscEducationGateway<F> {
    fun MiscEducationDto.save(): ResultE<F, List<MiscEducation>>
    fun AddMiscEducationDto.add(): ResultE<F, List<MiscEducation>>
    fun RemoveMiscEducationDto.remove(): ResultE<F, List<MiscEducation>>
    fun GetMiscEducationDto.all(): ResultE<F, List<MiscEducation>>
}

interface LanguageGateway<F> {
    fun LanguageDto.save(): ResultE<F, List<Language>>
    fun AddLanguageDto.add(): ResultE<F, List<Language>>
    fun RemoveLanguageDto.remove(): ResultE<F, List<Language>>
    fun GetLanguageDto.all(): ResultE<F, List<Language>>
}

interface ProficiencyGateway<F> {
    fun GetProficiencyDto.all(): ResultE<F, List<Proficiency>>
}