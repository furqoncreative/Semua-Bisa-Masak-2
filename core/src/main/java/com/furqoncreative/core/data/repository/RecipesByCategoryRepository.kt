package com.furqoncreative.core.data.repository

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.data.NetworkBoundResource
import com.furqoncreative.core.data.source.local.LocalDataSource
import com.furqoncreative.core.data.source.remote.RemoteDataSource
import com.furqoncreative.core.data.source.remote.network.ApiResponse
import com.furqoncreative.core.data.source.remote.response.recipesbycategory.RecipesByCategoryItem
import com.furqoncreative.core.domain.model.recipesbycategory.RecipesByCategory
import com.furqoncreative.core.domain.repository.recipesbycategory.IRecipesByCategoryRepository
import com.furqoncreative.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesByCategoryRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IRecipesByCategoryRepository {


    override fun getAllRecipesByCategory(param: String): Flow<Resource<List<RecipesByCategory>>> =
        object : NetworkBoundResource<List<RecipesByCategory>, List<RecipesByCategoryItem>>() {
            override fun loadFromDB(): Flow<List<RecipesByCategory>> {
                return localDataSource.getAllRecipesByCategory(param).map {
                    DataMapper.mapRecipesByCategoryEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<RecipesByCategory>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RecipesByCategoryItem>>> =
                remoteDataSource.getRecipesByCategory(param)

            override suspend fun saveCallResult(data: List<RecipesByCategoryItem>) {
                val recipesByCategoryList =
                    DataMapper.mapRecipesByCategoryResponsesToEntities(param, data)
                localDataSource.insertRecipesByCategory(recipesByCategoryList)
            }
        }.asFlow()
}
