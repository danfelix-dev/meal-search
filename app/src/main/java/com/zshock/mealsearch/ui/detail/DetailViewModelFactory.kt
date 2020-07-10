package com.zshock.mealsearch.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zshock.mealsearch.domain.model.Meal

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val meal: Meal) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(meal) as T
    }
}