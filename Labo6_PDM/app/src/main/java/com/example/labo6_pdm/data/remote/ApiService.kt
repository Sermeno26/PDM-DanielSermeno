package com.example.labo6_pdm.data.remote
import com.example.labo6_pdm.data.model.Meals
import retrofit2.http.GET

interface ApiService {

    @GET("https://www.themealdb.com/api/json/v1/1/search.php?s=")
    suspend fun getUsers(): Meals
}