package com.acv.manfred.curriculum.ui.mapper

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.gategay.ResultK
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.toView

interface QuestionnaireViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<Questionnaire>>>.toViewQuestionnaire(): Kind<F, ResultK<List<QuestionnaireModel>>> =
        flatMap { catch { it.map { list -> list.map { questionnaire -> questionnaire.toView() } } } }
}