package com.unated.academy.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun toActorsList(value: String) : ArrayList<Actor> {
        val token: TypeToken<ArrayList<Actor>> = object : TypeToken<ArrayList<Actor>>() {}
        return Gson().fromJson(value, token.type)
    }

    @TypeConverter
    fun toActorsString(value: ArrayList<Actor>): String = Gson().toJson(value)

    @TypeConverter
    fun toGenresList(value: String) : ArrayList<Genre> {
        val token: TypeToken<ArrayList<Genre>> = object : TypeToken<ArrayList<Genre>>() {}
        return Gson().fromJson(value, token.type)
    }

    @TypeConverter
    fun toGenreString(value: ArrayList<Genre>): String = Gson().toJson(value)

    @TypeConverter
    fun toIntList(value: String) : ArrayList<Int> {
        val token: TypeToken<ArrayList<Int>> = object : TypeToken<ArrayList<Int>>() {}
        return Gson().fromJson(value, token.type)
    }

    @TypeConverter
    fun toIntStr(value: ArrayList<Int>): String = Gson().toJson(value)
}