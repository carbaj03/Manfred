package com.acv.manfred.curriculum.ui.form.components

import arrow.core.None
import arrow.core.Option
import arrow.core.some
import java.util.*

interface DateFormatter {
    fun Calendar.getDisplayFormat(): String =
            DateDisplay(this).value

    fun String.createCalendarFromFormatter(): Option<Calendar> =
            when (val display: DisplayFormat = DisplayFormat(this)) {
                is DateDisplay -> display.calendar.some()
                is NormalDisplay -> None
            }
}

sealed class DisplayFormat(open val value: String) {
    companion object {
        operator fun invoke(text: String): DisplayFormat =
                when {
                    DateDisplay.isDisplayFormat(text) -> DateDisplay(text)
                    else -> NormalDisplay(text)
                }
    }
}

data class DateDisplay(override val value: String) : DisplayFormat(value) {
    companion object {
        const val regex = "^([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}\$"

        operator fun invoke(cal: Calendar): DateDisplay =
                DateDisplay("""${cal.dayDisplay()}/${cal.monthDisplay()}/${cal.get(Calendar.YEAR)}""")

        private fun Calendar.monthDisplay(): String =
                (get(Calendar.MONTH) + 1).run { if (this <= 9) "0$this" else this.toString() }

        private fun Calendar.dayDisplay(): String =
                get(Calendar.DAY_OF_MONTH).run { if (this <= 9) "0$this" else this.toString() }

        fun isDisplayFormat(text: String): Boolean =
                Regex(regex).matches(text)
    }

    val calendar
        get() =
            Calendar.getInstance().apply {
                val (day, month, year) = value.split("/")
                set(Calendar.YEAR, year.toInt())
                set(Calendar.MONTH, month.toInt() - 1)
                set(Calendar.DAY_OF_MONTH, day.toInt())
            }
}

data class NormalDisplay(override val value: String) : DisplayFormat(value)