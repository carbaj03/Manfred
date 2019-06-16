package com.acv.manfred.curriculum.ui.mapper

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Language
import com.acv.manfred.curriculum.presentation.form.component.language.LanguageModel
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel
import com.acv.manfred.curriculum.presentation.form.component.language.toView

interface LanguageViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<Language>>>.toView(): Kind<F, ResultK<List<LanguageModel>>> =
        flatMap { catch { it.map { list -> list.map { language -> language.toView() } } } }

    fun Kind<F, ResultK<List<Language>>>.toView(proficiencies : List<ProficiencyModel>): Kind<F, ResultK<List<LanguageModel>>> =
        flatMap { catch { it.map { list -> list.map { language -> language.toView(proficiencies) } } } }
}