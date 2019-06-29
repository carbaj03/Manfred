package com.acv.manfred.curriculum.data.gateway.datasource.local

import androidx.room.TypeConverter
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList
import com.google.gson.Gson
import java.util.*
import java.util.Collections.emptyList


class Converters {
    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

//
//    @TypeConverter fun enumProficiency(calendar: ProficiencyEntity): String = calendar.value
//
//    @TypeConverter fun stringToProficiency(id: String, value: String): ProficiencyEntity =
//            ProficiencyEntity(id, value)

    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<String> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<String>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<String>): String {
        return gson.toJson(someObjects)
    }

}
