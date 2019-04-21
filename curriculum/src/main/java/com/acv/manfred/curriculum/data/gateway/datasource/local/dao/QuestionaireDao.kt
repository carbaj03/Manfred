package com.acv.manfred.curriculum.data.gateway.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.QuestionnaireEntity

@Dao
interface QuestionaireDao {
    @Query("SELECT * FROM questionaire ORDER BY id")
    fun getQuestionaire(): LiveData<List<QuestionnaireEntity>>

//    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
//    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int): LiveData<List<Plant>>
//
//    @Query("SELECT * FROM plants WHERE id = :plantId")
//    fun getPlant(plantId: String): LiveData<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<QuestionnaireEntity>)

    @Delete
    fun delete(questionnaire: QuestionnaireEntity)
}
