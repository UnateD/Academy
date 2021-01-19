package com.unated.academy

import com.unated.academy.data.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AppApi {

    @GET("configuration")
    suspend fun getConfiguration() : Configuration

    @GET("movie/top_rated")
    suspend fun getMovies(@Query("page") page: Int): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: Int): MovieDetails

    @GET("genre/movie/list")
    suspend fun getGenres() : GenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(@Path("movie_id") movie_id: Int): ActorsResponse

}

object Retrofit {
    val appApi: AppApi = networkClient().create(AppApi::class.java)
}

private fun networkClient(): Retrofit {

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val originalHttpUrl: HttpUrl = request.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val newReq = request.newBuilder().url(url).build()

            chain.proceed(newReq)
        }
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder().apply {
        baseUrl(BuildConfig.BASE_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()
}