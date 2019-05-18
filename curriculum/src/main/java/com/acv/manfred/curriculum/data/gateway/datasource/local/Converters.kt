package com.acv.manfred.curriculum.data.gateway.datasource.local

import androidx.room.TypeConverter
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity
import java.util.Calendar

class Converters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

//
//    @TypeConverter fun enumProficiency(calendar: ProficiencyEntity): String = calendar.value
//
//    @TypeConverter fun stringToProficiency(id: String, value: String): ProficiencyEntity =
//            ProficiencyEntity(id, value)
}