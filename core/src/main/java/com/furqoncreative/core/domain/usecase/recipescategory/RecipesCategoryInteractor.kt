package com.furqoncreative.core.domain.usecase.recipescategory

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipescategory.RecipesCategory
import com.furqoncreative.core.domain.repository.recipescategory.IRecipesCategoryRepository
import kotlinx.coroutines.flow.Flow

class RecipesCategoryInteractor(private val repository: IRecipesCategoryRepository) :
    RecipesCategoryUseCase {

    override fun getRecipesCategory(): Flow<Resource<List<RecipesCategory>>> =
        repository.getAllRecipesCategory()

}