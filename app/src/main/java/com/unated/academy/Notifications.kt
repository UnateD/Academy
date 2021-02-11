package com.unated.academy

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.unated.academy.activity.MainActivity
import com.unated.academy.data.Movie

interface INotifications {
    fun initialize()
    fun showNotification(movie: Movie)
}

private const val CHANNEL_NEW_MOVIES = "new_movies"

private const val REQUEST_CONTENT = 1

private const val MOVIE_TAG = "movie"

class Notifications(private val context: Context) : INotifications {

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    override fun initialize() {
        if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MOVIES) == null) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannelCompat.Builder(
                    CHANNEL_NEW_MOVIES,
                    NotificationManagerCompat.IMPORTANCE_HIGH
                )
                    .setName("New movies")
                    .build()
            )
        }
    }

    override fun showNotification(movie: Movie) {
        val contentUri = "https://android.example.com/chat/${movie.id}".toUri()

        val builder = NotificationCompat.Builder(context, CHANNEL_NEW_MOVIES)
            .setContentTitle(movie.title)
            .setContentText(movie.overview)
            .setSmallIcon(R.drawable.ic_fav_selected)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    REQUEST_CONTENT,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )

        notificationManagerCompat.notify(MOVIE_TAG, movie.id, builder.build())
    }
}