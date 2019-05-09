package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.MiscEducationModel
import com.acv.manfred.curriculum.presentation.form.component.miscEducation.toView
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.QuestionnaireModel
import com.acv.manfred.curriculum.presentation.form.component.questionnaire.toView

interface QuestionnaireViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<Questionnaire>>>.toViewQuestionnaire(): Kind<F, ResultK<List<QuestionnaireModel>>> =
        flatMap { catch { it.map { list -> list.map { questionnaire -> questionnaire.toView() } } } }
}

interface MiscEducationViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<MiscEducation>>>.toView(): Kind<F, ResultK<List<MiscEducationModel>>> =
        flatMap { catch { it.map { list -> list.map { miscEducation -> miscEducation.toView() } } } }
}

interface ViewMapper<F> : MonadError<F, Throwable> {

}