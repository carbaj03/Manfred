package com.acv.manfred.curriculum.ui.form.navigation

import com.acv.manfred.curriculum.R

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