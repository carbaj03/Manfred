package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.MiscEducationEntity

@Dao
interface MiscEducationDao {
    @Query("SELECT * FROM misc_education")
    fun getMiscEducation(): List<MiscEducationEntity>

    @Query("SELECT * FROM misc_education WHERE id = :id")
    fun getMiscEducation(id: String): MiscEducationEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(miscEducation: MiscEducationEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(miscEducations: List<MiscEducationEntity>)

    @Update
    fun update(miscEducation: MiscEducationEntity)

    @Delete
    fun delete(miscEducation: MiscEducationEntity)
}
