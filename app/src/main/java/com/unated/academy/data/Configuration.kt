package com.unated.academy.data

import java.io.Serializable

data class Configuration(
    val images: Images
) : Serializable

data class Images(
    val base_url: String,
    val secure_base_url: String,
    val backdrop_sizes: ArrayList<String>,
    val logo_sizes: ArrayList<String>,
    val poster_sizes: ArrayList<String>,
    val profile_sizes: ArrayList<String>
) : Serializable