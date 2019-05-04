package com.acv.manfred.curriculum.domain

import arrow.Kind
import arrow.core.Either
import arrow.effects.ForIO
import com.acv.manfred.curriculum.domain.model.*
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireModel

typealias GatewayIO = CvGateway<ForIO>

typealias UsesCasesIO = CvUseCase<ForIO>

typealias Result<A> = Either<BaseError, A>

interface CvGateway<F> {
    fun GetCvDto.get(): Kind<F, Result<Example>>
    fun RolesDto.get(): Kind<F, Result<List<RoleProfile>>>
    fun ProficiencyDto.get(): Kind<F, Result<List<Proficiency>>>

    fun QuestionnaireDto.save() : Kind<F, Result<List<Questionnaire>>>
    fun AddQuestionnaireDto.add() : Kind<F, Result<List<Questionnaire>>>
    fun RemoveQuestionnaireDto.remove() : Kind<F, Result<List<Questionnaire>>>
    fun GetQuestionnaireDto.all() : Kind<F, Result<List<Questionnaire>>>
}

interface CvUseCase<F> {
    fun RemoveQuestionnaireDto.removeView() : Kind<F, Result<List<QuestionnaireModel>>>
    fun QuestionnaireDto.saveView() : Kind<F, Result<List<QuestionnaireModel>>>
    fun AddQuestionnaireDto.addView() : Kind<F, Result<List<QuestionnaireModel>>>
    fun GetQuestionnaireDto.allView() : Kind<F, Result<List<QuestionnaireModel>>>
}

