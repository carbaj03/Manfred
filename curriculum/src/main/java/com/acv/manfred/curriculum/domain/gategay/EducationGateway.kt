package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.model.Education

interface EducationGateway {
    suspend fun EducationDto.save(): ResultK<List<Education>>
    suspend fun AddEducationDto.add():  ResultK<List<Education>>
    suspend fun RemoveEducationDto.remove():  ResultK<List<Education>>
    suspend fun GetEducationDto.all():  ResultK<List<Education>>
}