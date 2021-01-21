package com.unated.academy.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "actors")
data class Actor(
    @PrimaryKey
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val picture: String
)