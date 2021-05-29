package com.furqoncreative.core.data.repository

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.data.NetworkBoundResource
import com.furqoncreative.core.data.source.local.LocalDataSource
import com.furqoncreative.core.data.source.remote.RemoteDataSource
import com.furqoncreative.core.data.source.remote.network.ApiResponse
import com.furqoncreative.core.data.source.remote.response.recipescategory.RecipesCategoryItem
import com.furqoncreative.core.domain.model.recipescategory.RecipesCategory
import com.furqoncreative.core.domain.repository.recipescategory.IRecipesCategoryRepository
import com.furqoncreative.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesCategoryRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IRecipesCategoryRepository {

    override fun getAllRecipesCategory(): Flow<Resource<List<RecipesCategory>>> =
        object : NetworkBoundResource<List<RecipesCategory>, List<RecipesCategoryItem>>() {
            override fun loadFromDB(): Flow<List<RecipesCategory>> {
                return localDataSource.getAllRecipesCategory().map {
                    DataMapper.mapRecipesCategoryEntityToDomain(it.drop(1))
                }
            }

            override fun shouldFetch(data: List<RecipesCategory>?): Boolean =  data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<RecipesCategoryItem>>> =
                remoteDataSource.getAllRecipesCategory()

            override suspend fun saveCallResult(data: List<RecipesCategoryItem>) {
                val recipesCategoryList = DataMapper.mapRecipesCategoryResponsesToEntities(data)
                localDataSource.insertRecipesCategory(recipesCategoryList)
            }
        }.asFlow()
}

