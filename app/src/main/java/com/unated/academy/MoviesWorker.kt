package com.unated.academy

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.unated.academy.data.AppDatabase

class MoviesWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    private val notifications: INotifications = Notifications(context)

    init {
        notifications.initialize()
    }

    override suspend fun doWork(): Result {
        val repository = MoviesRepository(AppDatabase.create(applicationContext))
        notifications.showNotification(repository.getRemoteForWorker())
        return Result.success()
    }
}