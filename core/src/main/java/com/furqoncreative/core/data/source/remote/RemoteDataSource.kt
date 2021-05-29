package com.furqoncreative.core.data.source.remote

import android.util.Log
import com.furqoncreative.core.data.source.remote.network.ApiResponse
import com.furqoncreative.core.data.source.remote.network.ApiService
import com.furqoncreative.core.data.source.remote.response.recipedetail.RecipeDetail
import com.furqoncreative.core.data.source.remote.response.recipesbycategory.RecipesByCategoryItem
import com.furqoncreative.core.data.source.remote.response.recipesbysearch.RecipesBySearchItem
import com.furqoncreative.core.data.source.remote.response.recipescategory.RecipesCategoryItem
import com.furqoncreative.core.data.source.remote.response.recipeslist.RecipesListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllRecipes(): Flow<ApiResponse<List<RecipesListItem>>> {
        return flow {
            try {
                val response = apiService.getAllRecipes()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRecipesByCategory(param: String): Flow<ApiResponse<List<RecipesByCategoryItem>>> {
        return flow {
            try {
                val response = apiService.getAllRecipesByCategory(param)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRecipesBySearch(param: String): Flow<ApiResponse<List<RecipesBySearchItem>>> {
        return flow {
            try {
                val response = apiService.getAllRecipesBySearch(param)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRecipe(param: String): Flow<ApiResponse<RecipeDetail>> {
        return flow {
            try {
                val response = apiService.getRecipe(param)
                val dataArray = response.recipeDetail
                if (dataArray != null) {
                    emit(ApiResponse.Success(response.recipeDetail))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllRecipesCategory(): Flow<ApiResponse<List<RecipesCategoryItem>>> {
        return flow {
            try {
                val response = apiService.getAllRecipesCategory()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

