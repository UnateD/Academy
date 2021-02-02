package com.unated.academy

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class MoviesWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("WORKER!", "START")
        val repository = MoviesRepository(applicationContext)
        Log.d("WORKER!", "call req")
        repository.getRemoteMovies(1)
        Log.d("WORKER!", "response")
        Log.d("WORKER!", "FINISH")
        return Result.success()
    }
}