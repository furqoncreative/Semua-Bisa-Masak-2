package com.furqoncreative.core.domain.usecase.recipesbysearch

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbysearch.RecipesBySearch
import kotlinx.coroutines.flow.Flow

interface RecipesBySearchUseCase {
    fun getRecipesBySearch(param: String): Flow<Resource<List<RecipesBySearch>>>
}