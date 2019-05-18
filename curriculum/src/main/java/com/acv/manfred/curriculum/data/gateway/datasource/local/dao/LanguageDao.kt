package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.LanguageEntity
import com.acv.manfred.curriculum.domain.model.Language

@Dao
interface LanguageDao {
    @Query("SELECT * FROM language ORDER BY language")
    fun getLanguage(): List<LanguageEntity>

    @Query("SELECT * FROM language WHERE id = :id")
    fun getLanguage(id: String): LanguageEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(language: LanguageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(languages: List<LanguageEntity>)

    @Delete
    fun delete(language: LanguageEntity)
}
