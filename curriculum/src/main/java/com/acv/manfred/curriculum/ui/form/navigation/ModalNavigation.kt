package com.acv.manfred.curriculum.ui.form.navigation

import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import com.acv.manfred.curriculum.ui.form.*
import com.acv.manfred.curriculum.ui.form.author.AuthorFragment
import com.acv.manfred.curriculum.ui.form.author.IntroFragment
import com.acv.manfred.curriculum.ui.form.education.EducationFragment
import com.acv.manfred.curriculum.ui.form.languaje.LanguageFragment
import com.acv.manfred.curriculum.ui.form.misceducation.MiscEducationFragment
import com.acv.manfred.curriculum.ui.form.questionnaire.QuestionnaireFragment

interface ModalNavigation : Launcher, Navigation {
    override val baseActivity: BaseActivity

    fun MainNavigation.navigate(): Unit =
        when (this) {
            Author -> baseActivity.run { createFragment<AuthorFragment>().load() }
            Experience -> baseActivity.run { createFragment<ExperienceFragment>().load() }
            Education -> baseActivity.run { createFragment<EducationFragment>().load() }
            Language -> baseActivity.run { createFragment<LanguageFragment>().load() }
            MiscEducation -> baseActivity.run { createFragment<MiscEducationFragment>().load() }
            Questionnaire -> baseActivity.run { createFragment<QuestionnaireFragment>().load() }
            Error -> baseActivity.run { createFragment<IntroFragment>().load() }
        }
}