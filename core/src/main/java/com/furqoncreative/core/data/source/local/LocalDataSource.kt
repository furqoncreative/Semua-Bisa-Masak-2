package com.furqoncreative.core.data.source.local

import com.furqoncreative.core.data.source.local.entity.recipe.RecipeEntity
import com.furqoncreative.core.data.source.local.entity.recipes.RecipesEntity
import com.furqoncreative.core.data.source.local.entity.recipesbycategory.RecipesByCategoryEntity
import com.furqoncreative.core.data.source.local.entity.recipesbysearch.RecipesBySearchEntity
import com.furqoncreative.core.data.source.local.entity.recipescategory.RecipesCategoryEntity
import com.furqoncreative.core.data.source.local.room.*
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val recipesByCategoryDao: RecipesByCategoryDao,
    private val recipesDao: RecipesDao,
    private val recipesCategoryDao: RecipesCategoryDao,
    private val recipesBySearchDao: RecipesBySearchDao,
    private val recipeDao: RecipeDao
) {

    /** RECIPES*/
    fun getAllRecipes(): Flow<List<RecipesEntity>> = recipesDao.getAllRecipes()

    suspend fun insertRecipes(recipes: List<RecipesEntity>) =
        recipesDao.insertRecipes(recipes)


    /** RECIPES BY CATEGORY*/
    fun getAllRecipesByCategory(param: String): Flow<List<RecipesByCategoryEntity>> =
        recipesByCategoryDao.getAllRecipesByCategory(param)

    suspend fun insertRecipesByCategory(recipes: List<RecipesByCategoryEntity>) =
        recipesByCategoryDao.insertRecipesByCategory(recipes)

    /** RECIPES CATEGORY*/
    fun getAllRecipesCategory(): Flow<List<RecipesCategoryEntity>> =
        recipesCategoryDao.getAllRecipesCategory()

    suspend fun insertRecipesCategory(recipesCaegory: List<RecipesCategoryEntity>) =
        recipesCategoryDao.insertRecipesCategory(recipesCaegory)

    /** RECIPES SEARCH*/
    fun getAllRecipesBySearch(param: String): Flow<List<RecipesBySearchEntity>> =
        recipesBySearchDao.getAllRecipesBySearch(param)

    suspend fun insertRecipesBySearch(recipes: List<RecipesBySearchEntity>) =
        recipesBySearchDao.insertRecipesBySearch(recipes)

    /** RECIPE DETTAIL*/
    fun getRecipe(param:String): Flow<RecipeEntity> = recipeDao.getRecipe(param)

    suspend fun insertRecipe(recipe: RecipeEntity) =
        recipeDao.insertRecipe(recipe)

    fun getFavoriteRecipes(): Flow<List<RecipeEntity>> = recipeDao.getFavoriteRecipe()

    fun setFavoriteRecipes(recipe: RecipeEntity, newState: Boolean) {
        recipe.isFavorite = newState
        recipeDao.updateFavoriteRecipe(recipe)
    }
}