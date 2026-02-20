package com.the.trackify.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.the.trackify.data.room.LocationDao
import com.the.trackify.data.apiservise.ApiService
import com.the.trackify.data.apiservise.LocationDto

class LocationSyncWorker (
    context: Context,
    params: WorkerParameters,
    private val dao: LocationDao,
    private val api: ApiService

) : CoroutineWorker(context, params) {

//    private val dao = AppDatabase.get(context).locationDao()

    override suspend fun doWork(): Result {

        return try {
            val unsynced = dao.getUnsynced()
            if (unsynced.isEmpty()) return Result.success()

            val dto = unsynced.map {
                LocationDto(it.latitude, it.longitude, it.timestamp)
            }

            val response = api.sendLocations(dto)

            if (response.isSuccessful) {
                dao.markAsSynced()
                Result.success()
            } else {
                Result.retry()
            }
        }catch (e: Exception){
            Result.retry()
        }


//        unsynced.forEach {
//            Log.d("WORKER", "Send: ${it.latitude}, ${it.longitude}")
//        }

//        dao.markSynced(unsynced.map { it.id })

//        return Result.success()
    }

}