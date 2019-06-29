package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    suspend fun getProfile(): List<ProfileEntity>

    @Query("SELECT * FROM profile WHERE id = :id")
    suspend fun getProfile(id: String): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: ProfileEntity) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(profile: List<ProfileEntity>)

    @Update
    suspend fun update(profile: ProfileEntity)

    @Delete
    suspend fun delete(profile: ProfileEntity)
}
