package com.zshock.mealsearch.data.network

import com.zshock.mealsearch.data.repository.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchClient {

    @GET("search.php")
    fun fetchMeals(@Query("s") query: String): Call<SearchResponse>

    @GET("random.php")
    fun fetchRandomMeal(): Call<SearchResponse>

}