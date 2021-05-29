package com.furqoncreative.core.domain.repository.recipe

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipe.Recipe
import kotlinx.coroutines.flow.Flow


interface IRecipeRecpository {

    fun getRecipe(param: String): Flow<Resource<Recipe>>

    fun getFavoriteRecipes(): Flow<List<Recipe>>

    fun setFavoriteRecipes(recipes: Recipe, state: Boolean)
}