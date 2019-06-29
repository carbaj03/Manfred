package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.core.Either
import arrow.core.Try
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.*
import com.acv.manfred.curriculum.domain.gategay.ResultE
import com.acv.manfred.curriculum.domain.model.*


interface DomainMapper<F> : MonadError<F, Throwable> {

    fun ResultE<F, ExampleResponse>.toDomainExample(): ResultE<F, Example> =
        flatMap { catch { it.map { it.toDomain() } } }

    fun ResultE<F, List<RoleProfileResponse>>.toDomainRolePofile(): ResultE<F, List<RoleProfile>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun ResultE<F, List<ProficiencyEntity>>.toDomainProficiency(): ResultE<F, List<Proficiency>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun ResultE<F, List<QuestionnaireEntity>>.toDomainQuestionnaire(): ResultE<F, List<Questionnaire>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun ResultE<F, List<MiscEducationEntity>>.toDomainMiscEducation(): ResultE<F, List<MiscEducation>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }


    fun ResultE<F, List<LanguageEntity>>.toDomainLanguage(): ResultE<F, List<Language>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }
}


suspend fun <R> Either.Companion.catch(f: suspend () -> R): Either<Throwable, R> {
    return Try.invoke { f() }.toEither()
 }

