package com.zshock.mealsearch.data.repository

import androidx.lifecycle.MutableLiveData
import com.zshock.mealsearch.data.network.Api
import com.zshock.mealsearch.domain.model.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealRepository {

    var meals: MutableLiveData<BaseResponse<List<Meal>>> = MutableLiveData()
    var currentCall: Call<SearchResponse>? = null

    fun queryMeals(query: String) {
        if (currentCall != null) {
            currentCall?.cancel()
        }

        currentCall = Api.getInstance().fetchMeals(query)
        currentCall?.enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                if (!call.isCanceled) {
                    meals.value = BaseResponse(t)
                }
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                meals.value = BaseResponse(response.body()?.results)
            }
        })
    }

}