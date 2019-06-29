package com.acv.manfred.curriculum.presentation.operation

import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.presentation.form.component.education.EducationModel

interface EducationUseCase {
    suspend fun RemoveEducationDto.removeView(): ResultK<List<EducationModel>>
    suspend fun EducationDto.saveView(): ResultK<List<EducationModel>>
    suspend fun AddEducationDto.addView(): ResultK<List<EducationModel>>
    suspend fun GetEducationDto.allView(): ResultK<List<EducationModel>>
}