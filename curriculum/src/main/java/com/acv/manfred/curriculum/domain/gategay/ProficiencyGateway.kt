package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.GetProficiencyDto
import com.acv.manfred.curriculum.domain.model.Proficiency

interface ProficiencyGateway<F> {
    fun GetProficiencyDto.all(): ResultE<F, List<Proficiency>>
}