package com.furqoncreative.semuabisamasak.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.furqoncreative.core.domain.usecase.recipes.RecipesUseCase
import com.furqoncreative.core.domain.usecase.recipescategory.RecipesCategoryUseCase

class HomeViewModel(
    recipesUseCase: RecipesUseCase,
    categoryUseCase: RecipesCategoryUseCase
) :
    ViewModel() {
    val recipes = recipesUseCase.getAllRecipes().asLiveData()

    val category = categoryUseCase.getRecipesCategory().asLiveData()
}

