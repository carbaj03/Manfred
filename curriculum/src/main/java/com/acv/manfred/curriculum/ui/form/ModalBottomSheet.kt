package com.acv.manfred.curriculum.ui.form

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.acv.manfred.curriculum.R
import com.acv.manfred.curriculum.ui.common.activity.BaseActivity
import com.acv.manfred.curriculum.ui.common.fragment.createFragment
import com.acv.manfred.curriculum.ui.common.navigator.Launcher
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.modal_bottom_sheet.*

class BottomSheetDialogFragment : AppCompatDialogFragment(), ModalNavigation {
    override val baseActivity: BaseActivity get() = activity as BaseActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.modal_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation_view.setNavigationItemSelectedListener { menuItem ->
            MainNavigation(menuItem.itemId).navigate()
            dismiss()
            true
        }
    }
}

interface ModalNavigation : Launcher {
    override val baseActivity: BaseActivity

    fun MainNavigation.navigate(): Unit =
        when (this) {
            Author -> baseActivity.run { createFragment<AuthorFragment>().load() }
            Experience -> baseActivity.run { createFragment<ExperienceFragment>().load() }
            Education -> baseActivity.run { createFragment<EducationFragment>().load() }
            Language -> baseActivity.run { createFragment<LanguageFragment>().load() }
            MiscEducation -> baseActivity.run { createFragment<MiscEducationFragment>().load() }
            Questionnaire -> baseActivity.run { createFragment<QuestionaireFragment>().load() }
            Error -> baseActivity.run { createFragment<IntroFragment>().load() }
        }
}

sealed class MainNavigation(val id: Int) {
    companion object {
        operator fun invoke(id: Int): MainNavigation =
            when (id) {
                Author.id -> Author
                Experience.id -> Experience
                Education.id -> Education
                Language.id -> Language
                MiscEducation.id -> MiscEducation
                Questionnaire.id -> Questionnaire
                else -> Error
            }
    }
}

object Author : MainNavigation(R.id.author)
object Experience : MainNavigation(R.id.experience)
object Education : MainNavigation(R.id.education)
object Language : MainNavigation(R.id.languages)
object MiscEducation : MainNavigation(R.id.misc_education)
object Questionnaire : MainNavigation(R.id.questionnaire)
object Error : MainNavigation(R.id.error)