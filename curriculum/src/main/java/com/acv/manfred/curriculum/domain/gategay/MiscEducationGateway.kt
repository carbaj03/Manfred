package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.AddMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.GetMiscEducationDto
import com.acv.manfred.curriculum.domain.dto.MiscEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveMiscEducationDto
import com.acv.manfred.curriculum.domain.model.MiscEducation

interface MiscEducationGateway<F> {
    fun MiscEducationDto.save(): ResultE<F, List<MiscEducation>>
    fun AddMiscEducationDto.add(): ResultE<F, List<MiscEducation>>
    fun RemoveMiscEducationDto.remove(): ResultE<F, List<MiscEducation>>
    fun GetMiscEducationDto.all(): ResultE<F, List<MiscEducation>>
}