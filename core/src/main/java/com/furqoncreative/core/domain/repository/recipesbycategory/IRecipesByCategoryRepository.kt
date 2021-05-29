package com.furqoncreative.core.domain.repository.recipesbycategory

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbycategory.RecipesByCategory
import kotlinx.coroutines.flow.Flow

interface IRecipesByCategoryRepository {

    fun getAllRecipesByCategory(param: String): Flow<Resource<List<RecipesByCategory>>>

}