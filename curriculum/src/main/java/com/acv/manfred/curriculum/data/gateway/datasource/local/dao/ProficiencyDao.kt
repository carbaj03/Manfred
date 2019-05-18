package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.ProficiencyEntity

@Dao
interface ProficiencyDao {

    @Query("SELECT * FROM proficiency")
    fun getProficiency(): List<ProficiencyEntity>

}
