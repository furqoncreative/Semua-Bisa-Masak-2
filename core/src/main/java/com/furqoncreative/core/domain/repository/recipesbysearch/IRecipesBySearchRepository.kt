package com.furqoncreative.core.domain.repository.recipesbysearch

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbysearch.RecipesBySearch
import kotlinx.coroutines.flow.Flow

interface IRecipesBySearchRepository {

    fun getAllRecipesBySearch(param: String): Flow<Resource<List<RecipesBySearch>>>

}