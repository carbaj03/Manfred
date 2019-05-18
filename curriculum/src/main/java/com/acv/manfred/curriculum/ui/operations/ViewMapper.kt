package com.acv.manfred.curriculum.ui.operations

import arrow.Kind
import arrow.typeclasses.MonadError
import com.acv.manfred.curriculum.domain.ResultK
import com.acv.manfred.curriculum.domain.model.Language
import com.acv.manfred.curriculum.domain.model.MiscEducation
import com.acv.manfred.curriculum.domain.model.Proficiency
import com.acv.manfred.curriculum.domain.model.Questionnaire
import com.acv.manfred.curriculum.presentation.form.component.language.LanguageModel
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.ProficiencyModel
import com.acv.manfred.curriculum.presentation.form.component.language.proficiency.toView
import com.acv.manfred.curriculum.presentation.form.component.language.toView
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

interface LanguageViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<Language>>>.toView(): Kind<F, ResultK<List<LanguageModel>>> =
        flatMap { catch { it.map { list -> list.map { language -> language.toView() } } } }

    fun Kind<F, ResultK<List<Language>>>.toView(proficiencies : List<ProficiencyModel>): Kind<F, ResultK<List<LanguageModel>>> =
        flatMap { catch { it.map { list -> list.map { language -> language.toView(proficiencies) } } } }
}

interface ProficiencyViewMapper<F> : MonadError<F, Throwable> {
    fun Kind<F, ResultK<List<Proficiency>>>.toViewP(): Kind<F, ResultK<List<ProficiencyModel>>> =
        flatMap { catch { it.map { list -> list.map { language -> language.toView() } } } }
}

interface ViewMapper<F> : MonadError<F, Throwable>