package com.acv.manfred.curriculum.data.gateway.datasource

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ExampleResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.ProficiencyResponse
import com.acv.manfred.curriculum.data.gateway.datasource.api.model.RoleProfileResponse
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.domain.model.RoleProfile


interface DomainMapper<F> : MonadError<F, Throwable> {

    fun Kind<F, Result<ExampleResponse>>.toDomainExample(): Kind<F, Result<Example>> =
        flatMap { catch { it.map { it.toDomain() } } }

    fun RoleProfileT<F>.toDomainRolePofile(): Kind<F, Result<List<RoleProfile>>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }

    fun ProficiencyProfileT<F>.toDomainProficiency(): Kind<F, Result<List<Proficiency>>> =
        flatMap { catch { it.map { it.map { it.toDomain() } } } }
}

typealias RoleProfileT<F> = Kind<F, Result<List<RoleProfileResponse>>>
typealias ProficiencyProfileT<F> = Kind<F, Result<List<ProficiencyResponse>>>

