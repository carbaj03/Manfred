package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity


@Dao
interface QuestionaireDao {
    @Query("SELECT * FROM questionnaire")
    fun getQuestionaire(): List<QuestionnaireEntity>

    @Query("SELECT * FROM questionnaire WHERE id = :id")
    fun getQuestionaire(id: String): QuestionnaireEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(questionnaire: QuestionnaireEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(plants: List<QuestionnaireEntity>)

    @Delete
    fun delete(questionnaire: QuestionnaireEntity)

    @Update
    fun update(questionnaire: QuestionnaireEntity)
}