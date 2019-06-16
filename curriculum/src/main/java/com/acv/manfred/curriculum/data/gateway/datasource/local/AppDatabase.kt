package com.acv.manfred.curriculum.data.gateway.datasource.local

import android.content.Context
import androidx.room.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.common.DATABASE_NAME
import com.acv.manfred.curriculum.data.gateway.datasource.local.dao.*
import com.acv.manfred.curriculum.data.gateway.datasource.local.model.*
import com.acv.manfred.curriculum.domain.model.Proficiency

/**
 * The Room database for this app
 */
@Database(
    entities = [
        QuestionnaireEntity::class,
        MiscEducationEntity::class,
        LanguageEntity::class,
        ProficiencyEntity::class,
        EducationEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionaireDao(): QuestionaireDao
    abstract fun languageDao(): LanguageDao
    abstract fun proficiencyDao(): ProficiencyDao
    abstract fun miscEducationDao(): MiscEducationDao
    abstract fun educationDao(): EducationDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//                    .addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                            WorkManager.getInstance().enqueue(request)
//                        }
//                    })
                .build()
    }
}
