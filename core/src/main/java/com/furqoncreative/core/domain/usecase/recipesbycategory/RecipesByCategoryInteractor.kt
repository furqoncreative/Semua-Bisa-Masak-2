package com.furqoncreative.core.domain.usecase.recipesbycategory

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipesbycategory.RecipesByCategory
import com.furqoncreative.core.domain.repository.recipesbycategory.IRecipesByCategoryRepository
import kotlinx.coroutines.flow.Flow

class RecipesByCategoryInteractor(private val repository: IRecipesByCategoryRepository) :
    RecipesByCategoryUseCase {

    override fun getRecipesByCategory(param: String): Flow<Resource<List<RecipesByCategory>>> =
        repository.getAllRecipesByCategory(param)

}