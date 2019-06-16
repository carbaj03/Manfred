package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.AddLanguageDto
import com.acv.manfred.curriculum.domain.dto.GetLanguageDto
import com.acv.manfred.curriculum.domain.dto.LanguageDto
import com.acv.manfred.curriculum.domain.dto.RemoveLanguageDto
import com.acv.manfred.curriculum.domain.model.Language

interface LanguageGateway<F> {
    fun LanguageDto.save(): ResultE<F, List<Language>>
    fun AddLanguageDto.add(): ResultE<F, List<Language>>
    fun RemoveLanguageDto.remove(): ResultE<F, List<Language>>
    fun GetLanguageDto.all(): ResultE<F, List<Language>>
}