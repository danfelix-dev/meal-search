package com.zshock.mealsearch.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Meal : Parcelable {
    @SerializedName("idMeal")
    val id: String? = null

    @SerializedName("strMeal")
    val title: String? = null

    @SerializedName("strMealThumb")
    val thumbnailUrl: String? = null

    @SerializedName("strCategory")
    val category: String? = null

    @SerializedName("strInstructions")
    val instructions: String? = null

    @SerializedName("strYoutube")
    val videoUrl: String? = null

    var ingredients: List<String>? = null
}