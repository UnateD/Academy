package com.unated.academy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Actor::class, Genre::class, Movie::class, MovieDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: MoviesDao

    companion object {
        fun create(context: Context) : AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "MovieDatabase").build()
    }
}