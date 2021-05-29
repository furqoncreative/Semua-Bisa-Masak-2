package com.furqoncreative.semuabisamasak.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipe.Recipe
import com.furqoncreative.core.domain.usecase.recipe.RecipeUseCase


class RecipeDetailViewModel(
    private val recipeUseCase: RecipeUseCase
) :
    ViewModel() {
    val recipe: (String) -> LiveData<Resource<Recipe>> = {
        recipeUseCase.getRecipe(it).asLiveData()
    }

    fun setFavoriteRecipes(recipe: Recipe, newStatus: Boolean) =
        recipeUseCase.setFavoriteRecipes(recipe, newStatus)
}
