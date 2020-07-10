package com.zshock.mealsearch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zshock.mealsearch.data.repository.MealRepository

class SearchViewModel : ViewModel() {
    private var mealRepository = MealRepository()
    var items = mealRepository.meals

    private var randomMealRepository = MealRepository()
    var randomItems = randomMealRepository.meals

    var query: String? = null

    init {
        randomMealRepository.queryRandomMeals(viewModelScope)
    }

    fun onQueryChanged(query: String) {
        this.query = query
        mealRepository.queryMeals(query)
    }
}