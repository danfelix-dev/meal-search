package com.zshock.mealsearch.data.network

import com.zshock.mealsearch.MealSearchApplication
import com.zshock.mealsearch.data.repository.SearchResponse
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Api {

    private var API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    private var client: MealSearchClient

    companion object {
        private var INSTANCE: Api? = null

        fun getInstance(): Api {
            if (INSTANCE == null) {
                INSTANCE = Api()
            }
            return INSTANCE!!
        }
    }

    init {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(MealSearchApplication.getContext().cacheDir, cacheSize)
        val httpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                val request = chain.request()
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                chain.proceed(request)
            }

        val builder: Retrofit.Builder = Retrofit.Builder()
        val retrofit: Retrofit = builder
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        client = retrofit.create()
    }

    fun fetchMeals(query: String): Call<SearchResponse> {
        return client.fetchMeals(query)
    }

}