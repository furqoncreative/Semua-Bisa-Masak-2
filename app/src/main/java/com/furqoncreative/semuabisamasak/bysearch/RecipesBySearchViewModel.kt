package com.furqoncreative.semuabisamasak.bysearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbysearch.RecipesBySearch
import com.furqoncreative.core.domain.usecase.recipesbysearch.RecipesBySearchUseCase


class RecipesBySearchViewModel(private val recipesBySearchUseCase: RecipesBySearchUseCase) :
    ViewModel() {
    val recipes: (String) -> LiveData<Resource<List<RecipesBySearch>>> = {
        recipesBySearchUseCase.getRecipesBySearch(it).asLiveData()
    }
}
