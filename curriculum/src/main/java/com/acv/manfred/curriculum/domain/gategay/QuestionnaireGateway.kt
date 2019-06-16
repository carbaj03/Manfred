package com.acv.manfred.curriculum.domain.gategay

import com.acv.manfred.curriculum.domain.dto.AddQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.GetQuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.QuestionnaireDto
import com.acv.manfred.curriculum.domain.dto.RemoveQuestionnaireDto
import com.acv.manfred.curriculum.domain.model.Questionnaire

interface QuestionnaireGateway<F> {
    fun QuestionnaireDto.save(): ResultE<F, List<Questionnaire>>
    fun AddQuestionnaireDto.add(): ResultE<F, List<Questionnaire>>
    fun RemoveQuestionnaireDto.remove(): ResultE<F, List<Questionnaire>>
    fun GetQuestionnaireDto.all(): ResultE<F, List<Questionnaire>>
}