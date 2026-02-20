package com.the.trackify.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.the.trackify.service.LocationService
import com.the.trackify.worker.LocationSyncWorker
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun startTracking() {
        startService()
        startWorker()
    }

    fun stopTracking() {
        stopService()
        stopWorker()
    }

    private fun startService() {
        val context = getApplication<Application>()
        val intent = Intent(context, LocationService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }

    private fun startWorker() {
        val request = PeriodicWorkRequestBuilder<LocationSyncWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(getApplication())
            .enqueueUniquePeriodicWork(
                "location_upload",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

    private fun stopWorker() {

        WorkManager.getInstance(getApplication())
            .cancelUniqueWork("location_upload")
    }

    private fun stopService() {
        val context = getApplication<Application>()
        context.stopService(Intent(context, LocationService::class.java))
    }
}