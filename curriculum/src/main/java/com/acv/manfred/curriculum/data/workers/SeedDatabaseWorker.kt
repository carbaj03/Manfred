//package com.acv.manfred.curriculum.data.workers
//
//import android.content.Context
//import android.util.Log
//import androidx.work.CoroutineWorker
//import androidx.work.WorkerParameters
//import com.acv.manfred.curriculum.data.gateway.datasource.local.AppDatabase
//import com.acv.manfred.curriculum.data.gateway.datasource.local.common.PLANT_DATA_FILENAME
//import com.acv.manfred.curriculum.ui.form.Questionaire
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.google.gson.stream.JsonReader
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.coroutineScope
//
//class SeedDatabaseWorker(
//    context: Context,
//    workerParams: WorkerParameters
//) : CoroutineWorker(context, workerParams) {
//
//    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }
//    override val coroutineContext = Dispatchers.IO
//
//    override suspend fun doWork(): Result = coroutineScope {
//
//        try {
//            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
//                JsonReader(inputStream.reader()).use { jsonReader ->
//                    val plantType = object : TypeToken<List<Questionaire>>() {}.type
//                    val questionaires: List<Questionaire> = Gson().fromJson(jsonReader, plantType)
//
//                    val database = AppDatabase.getInstance(applicationContext)
//                    database.questionaireDao().insertAll(questionaires)
//
//                    Result.success()
//                }
//            }
//        } catch (ex: Exception) {
//            Log.e(TAG, "Error seeding database", ex)
//            Result.failure()
//        }
//    }
//}