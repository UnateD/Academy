package com.unated.academy.data

import com.google.gson.annotations.SerializedName

data class Actor(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val picture: String
)