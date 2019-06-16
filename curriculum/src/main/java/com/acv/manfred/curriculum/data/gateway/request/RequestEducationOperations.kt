package com.acv.manfred.curriculum.data.gateway.request

import com.acv.manfred.curriculum.data.gateway.datasource.DomainMapperEducatoin
import com.acv.manfred.curriculum.data.gateway.network.NetworkEducationOperations
import com.acv.manfred.curriculum.domain.dto.AddEducationDto
import com.acv.manfred.curriculum.domain.dto.EducationDto
import com.acv.manfred.curriculum.domain.dto.GetEducationDto
import com.acv.manfred.curriculum.domain.dto.RemoveEducationDto
import com.acv.manfred.curriculum.domain.gategay.EducationGateway
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Education

interface RequestEducationOperations<N> : NetworkEducationOperations<N>, DomainMapperEducatoin, EducationGateway {

    override suspend fun EducationDto.save(): ResultK<List<Education>> =
        persist().toDomainEducation()

    override suspend fun GetEducationDto.all(): ResultK<List<Education>> =
        request().toDomainEducation()

    override suspend fun AddEducationDto.add(): ResultK<List<Education>> =
       persist().toDomainEducation()

    override suspend fun RemoveEducationDto.remove():  ResultK<List<Education>> =
        delete().toDomainEducation()
}