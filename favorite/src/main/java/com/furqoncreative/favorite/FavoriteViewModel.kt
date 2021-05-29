package com.furqoncreative.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.furqoncreative.core.domain.usecase.recipe.RecipeUseCase

class FavoriteViewModel(recipeUseCase: RecipeUseCase):ViewModel() {
    val favorite = recipeUseCase.getFavoriteRecipes().asLiveData()

}