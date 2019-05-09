package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.toDomain
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.model.*


interface DomainMapper<F> : MonadError<F, Throwable> {

    fun Kind<F, ResultK<ExampleResponse>>.toDomainExample(): Kind<F, ResultK<Example>> =
        flatMap { catch { it.map { it.toDomain() } } }

    fun RoleProfileT<F>.toDomainRolePofile(): Kind<F, ResultK<List<RoleProfile>>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun ProficiencyProfileT<F>.toDomainProficiency(): Kind<F, ResultK<List<Proficiency>>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun Kind<F, ResultK<List<QuestionnaireEntity>>>.toDomainQuestionnaire(): Kind<F, ResultK<List<Questionnaire>>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun Kind<F, ResultK<List<MiscEducationEntity>>>.toDomainMiscEducation(): Kind<F, ResultK<List<MiscEducation>>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }
}

typealias RoleProfileT<F> = Kind<F, ResultK<List<RoleProfileResponse>>>
typealias ProficiencyProfileT<F> = Kind<F, ResultK<List<ProficiencyResponse>>>

