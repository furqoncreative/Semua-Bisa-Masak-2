package com.furqoncreative.core.domain.repository.recipes

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipes.Recipes
import kotlinx.coroutines.flow.Flow

interface IRecipesRepository {

    fun getAllRecipes(): Flow<Resource<List<Recipes>>>

}