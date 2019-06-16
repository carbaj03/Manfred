package com.acv.manfred.curriculum.ui.operations

import com.acv.manfred.curriculum.data.gateway.request.RequestEducationOperations
import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.presentation.form.component.education.EducationModel
import com.acv.manfred.curriculum.presentation.operation.EducationUseCase
import com.acv.manfred.curriculum.ui.mapper.EducationViewMapper

interface EducationViewOperations<F, N> : RequestEducationOperations<N>, EducationViewMapper, EducationUseCase {

    override suspend fun EducationDto.saveView(): ResultK<List<EducationModel>> =
        save().toView()

    override suspend fun GetEducationDto.allView(): ResultK<List<EducationModel>> =
        all().toView()

    override suspend fun AddEducationDto.addView(): ResultK<List<EducationModel>> =
        add().toView()

    override suspend fun RemoveEducationDto.removeView(): ResultK<List<EducationModel>> =
        remove().toView()

}