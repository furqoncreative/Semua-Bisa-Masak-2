package com.furqoncreative.semuabisamasak.bycategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbycategory.RecipesByCategory
import com.furqoncreative.core.domain.usecase.recipesbycategory.RecipesByCategoryUseCase


class RecipesByCategoryViewModel(private val recipesByCategoryUseCase: RecipesByCategoryUseCase) :
    ViewModel() {
    val recipes: (String) -> LiveData<Resource<List<RecipesByCategory>>> = {
        recipesByCategoryUseCase.getRecipesByCategory(it).asLiveData()
    }

}
