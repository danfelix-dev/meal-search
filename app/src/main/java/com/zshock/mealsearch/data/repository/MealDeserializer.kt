package com.zshock.mealsearch.data.repository

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.zshock.mealsearch.domain.model.Meal
import java.lang.reflect.Type


class MealDeserializer : JsonDeserializer<Meal> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Meal {
        val jsonObject = json?.asJsonObject
        val meal = Gson().fromJson<Meal>(jsonObject, typeOfT)

        // This routine walks through every strIngredientX member and adds it to the ingredients
        // list of the final model. Neat, huh?
        val ingredientsList = mutableListOf<String>()
        val ingredientPrefix = "strIngredient"
        val measurePrefix = "strMeasure"

        for (i in 1..20) {
            val ingredient = jsonObject?.get("$ingredientPrefix$i")?.asString?.trim()
            val measure = jsonObject?.get("$measurePrefix$i")?.asString?.trim()
            if (ingredient.isNullOrBlank() || measure.isNullOrBlank()) {
                break
            } else {
                // I noticed the ingredients aren't capitalized, so let's make sure they are
                ingredientsList.add("${ingredient.capitalize()} ($measure)")
            }
        }
        meal.ingredients = ingredientsList.toList()

        return meal
    }

}