package com.furqoncreative.core.data.repository

import android.util.Log
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.data.NetworkBoundResource
import com.furqoncreative.core.data.source.local.LocalDataSource
import com.furqoncreative.core.data.source.remote.RemoteDataSource
import com.furqoncreative.core.data.source.remote.network.ApiResponse
import com.furqoncreative.core.data.source.remote.response.recipedetail.RecipeDetail
import com.furqoncreative.core.domain.model.recipe.Recipe
import com.furqoncreative.core.domain.repository.recipe.IRecipeRecpository
import com.furqoncreative.core.utils.AppExecutors
import com.furqoncreative.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRecpository {


    override fun getRecipe(param: String): Flow<Resource<Recipe>> =
        object : NetworkBoundResource<Recipe, RecipeDetail>() {
            override fun loadFromDB(): Flow<Recipe> {
                return localDataSource.getRecipe(param).map {
                    Log.d("TAG", "loadFromDB: $it ")
                    DataMapper.mapRecipeEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Recipe?): Boolean = data?.key == null

            override suspend fun createCall(): Flow<ApiResponse<RecipeDetail>> =
                remoteDataSource.getRecipe(param)

            override suspend fun saveCallResult(data: RecipeDetail) {
                val recipe = DataMapper.mapRecipeResponsesToEntities(param, data)
                Log.d("TAG", "saveCallResult: $data")
                Log.d("TAG", "saveCallResult: $recipe")
                localDataSource.insertRecipe(recipe)
            }
        }.asFlow()

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return localDataSource.getFavoriteRecipes().map {
            DataMapper.mapRecipeEntityToDomain(it)
        }
    }

    override fun setFavoriteRecipes(recipes: Recipe, state: Boolean) {
        val recipeEntity = DataMapper.mapRecipeDomainToEntity(recipes)
        appExecutors.diskIO().execute { localDataSource.setFavoriteRecipes(recipeEntity, state) }
    }
}