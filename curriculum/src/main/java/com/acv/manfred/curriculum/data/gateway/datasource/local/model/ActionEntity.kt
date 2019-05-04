package com.acv.manfred.curriculum.data.gateway.datasource.local.model

import androidx.room.*


@Entity(tableName = "state")
data class ActionEntity(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    var summary: String,
    var tools: String? = null
)
