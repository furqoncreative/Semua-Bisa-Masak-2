package com.furqoncreative.core.data.repository

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.data.NetworkBoundResource
import com.furqoncreative.core.data.source.local.LocalDataSource
import com.furqoncreative.core.data.source.remote.RemoteDataSource
import com.furqoncreative.core.data.source.remote.network.ApiResponse
import com.furqoncreative.core.data.source.remote.response.recipesbysearch.RecipesBySearchItem
import com.furqoncreative.core.domain.model.recipesbysearch.RecipesBySearch
import com.furqoncreative.core.domain.repository.recipesbysearch.IRecipesBySearchRepository
import com.furqoncreative.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesBySearchRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IRecipesBySearchRepository {


    override fun getAllRecipesBySearch(param: String): Flow<Resource<List<RecipesBySearch>>> =
        object : NetworkBoundResource<List<RecipesBySearch>, List<RecipesBySearchItem>>() {
            override fun loadFromDB(): Flow<List<RecipesBySearch>> {
                return localDataSource.getAllRecipesBySearch(param).map {
                    DataMapper.mapRecipesBySearchEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<RecipesBySearch>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RecipesBySearchItem>>> =
                remoteDataSource.getRecipesBySearch(param)

            override suspend fun saveCallResult(data: List<RecipesBySearchItem>) {
                val recipesBySearchList =
                    DataMapper.mapRecipesBySearchResponsesToEntities(data)
                localDataSource.insertRecipesBySearch(recipesBySearchList)
            }
        }.asFlow()
}
