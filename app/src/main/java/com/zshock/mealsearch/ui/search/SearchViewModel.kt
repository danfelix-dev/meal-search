package com.zshock.mealsearch.ui.search

import androidx.lifecycle.ViewModel
import com.zshock.mealsearch.data.repository.MealRepository

class SearchViewModel : ViewModel() {
    private var mealRepository = MealRepository()
    var items = mealRepository.meals
    var query: String? = null

    fun onQueryChanged(query: String) {
        this.query = query
        mealRepository.queryMeals(query)
    }
}