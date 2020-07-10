package com.zshock.mealsearch.data.repository

import com.google.gson.annotations.SerializedName
import com.zshock.mealsearch.domain.model.Meal

class SearchResponse {
    @SerializedName("meals")
    val results: List<Meal>? = null
}