package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.EducationEntity

@Dao
interface EducationDao {
    @Query("SELECT * FROM education")
    suspend fun getEducation(): List<EducationEntity>

    @Query("SELECT * FROM education WHERE id = :id")
    suspend fun getEducation(id: String): EducationEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(education: EducationEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(education: List<EducationEntity>)

    @Update
    suspend fun update(education: EducationEntity)

    @Delete
    suspend fun delete(education: EducationEntity)
}
