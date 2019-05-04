package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.Result
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.ui.form.components.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.ui.form.components.questionnaire.toView

interface ViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, Result<List<Questionnaire>>>.toViewQuestionnaire(): Kind<F, Result<List<QuestionnaireModel>>> =
        flatMap { catch { it.map { list -> list.map { questionnaire -> questionnaire.toView() } } } }
}
