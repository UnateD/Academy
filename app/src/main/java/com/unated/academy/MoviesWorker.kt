package com.unated.academy

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class MoviesWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val repository = MoviesRepository(applicationContext)
        repository.getRemoteMovies(1)
        return Result.success()
    }
}