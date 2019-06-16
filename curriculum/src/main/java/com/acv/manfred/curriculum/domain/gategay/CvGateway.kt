package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.GetCvDto
import com.acv.manfred.curriculum.domain.dto.RolesDto
import com.acv.manfred.curriculum.domain.model.Example
import com.acv.manfred.curriculum.domain.model.RoleProfile

interface CvGateway<F> {
    fun GetCvDto.get(): ResultE<F, Example>
    fun RolesDto.get(): ResultE<F, List<RoleProfile>>
}