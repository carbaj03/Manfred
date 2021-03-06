package com.acv.manfred.curriculum.data.gateway.request

import arrow.Kind
import arrow.fx.typeclasses.Async
import com.acv.manfred.curriculum.data.gateway.network.NetworkLanguageOperations
import com.acv.manfred.curriculum.data.gateway.network.NetworkProficiencyOperations
import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapper
import com.acv.manfred.curriculum.domain.gategay.LanguageGateway
import com.acv.manfred.curriculum.domain.gategay.ProficiencyGateway
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.domain.model.Language
import com.acv.manfred.curriculum.domain.model.Proficiency
import kotlin.coroutines.CoroutineContext

interface RequestLanguageOperations<F, N> : Async<F>, NetworkLanguageOperations<F, N>, DomainMapper<F>, LanguageGateway<F> {
    val ctx: CoroutineContext

    override fun LanguageDto.save(): Kind<F, ResultK<List<Language>>> =
        defer(ctx) { persist().toDomainLanguage() }

    override fun GetLanguageDto.all(): Kind<F, ResultK<List<Language>>> =
        defer(ctx) { request().toDomainLanguage() }

    override fun AddLanguageDto.add(): Kind<F, ResultK<List<Language>>> =
        defer(ctx) { persist().toDomainLanguage() }

    override fun RemoveLanguageDto.remove(): Kind<F, ResultK<List<Language>>> =
        defer(ctx) { delete().toDomainLanguage() }
}

interface RequestProficiencyOperations<F, N> : Async<F>, NetworkProficiencyOperations<F, N>, DomainMapper<F>, ProficiencyGateway<F> {
    val ctx: CoroutineContext

    override fun GetProficiencyDto.all(): Kind<F, ResultK<List<Proficiency>>> =
        defer(ctx) { request().toDomainProficiency() }
}