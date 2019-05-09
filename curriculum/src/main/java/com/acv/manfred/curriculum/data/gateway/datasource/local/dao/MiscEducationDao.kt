package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity
import com.acv.manfred.curriculum.domain.model.MiscEducation

@Dao
interface MiscEducationDao {
    @Query("SELECT * FROM misc_education")
    fun getMiscEducation(): List<MiscEducationEntity>

    @Query("SELECT * FROM misc_education WHERE id = :id")
    fun getMiscEducation(id: String): MiscEducationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(miscEducation: MiscEducationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(miscEducations: List<MiscEducationEntity>)

    @Delete
    fun delete(miscEducation: MiscEducationEntity)
}
