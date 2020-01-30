package com.acv.manfred.curriculum.presentation.operation

import arrow.fx.ForIO
import com.acv.manfred.curriculum.domain.dto.*
import com.acv.manfred.curriculum.presentation.form.component.language.LanguageModel
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel


typealias LanguageUsesCasesIO = LanguageUseCase<ForIO>
typealias ProficiencyUsesCasesIO = ProficiencyUseCase<ForIO>

interface LanguageUseCase<F> {
    fun RemoveLanguageDto.removeView() : Return<F, List<LanguageModel>>
    fun LanguageDto.saveView() : Return<F, List<LanguageModel>>
    fun AddLanguageDto.addView() : Return<F, List<LanguageModel>>
    fun GetLanguageDto.allView() : Return<F, List<LanguageModel>>
}

interface ProficiencyUseCase<F> {
    fun GetProficiencyDto.allView(): Return<F, List<ProficiencyModel>>
}