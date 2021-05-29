package com.furqoncreative.core.domain.usecase.recipesbycategory

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbycategory.RecipesByCategory
import kotlinx.coroutines.flow.Flow

interface RecipesByCategoryUseCase {
    fun getRecipesByCategory(param: String): Flow<Resource<List<RecipesByCategory>>>
}