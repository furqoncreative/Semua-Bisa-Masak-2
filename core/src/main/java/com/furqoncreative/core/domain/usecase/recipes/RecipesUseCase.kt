package com.furqoncreative.core.domain.usecase.recipes

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipes.Recipes
import kotlinx.coroutines.flow.Flow

interface RecipesUseCase {
    fun getAllRecipes(): Flow<Resource<List<Recipes>>>
}