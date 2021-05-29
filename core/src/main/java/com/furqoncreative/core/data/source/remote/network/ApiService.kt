package com.furqoncreative.core.data.source.remote.network

import com.furqoncreative.core.data.source.remote.response.recipedetail.RecipeDetailResponse
import com.furqoncreative.core.data.source.remote.response.recipesbycategory.RecipesByCategoryResponse
import com.furqoncreative.core.data.source.remote.response.recipesbysearch.RecipesBySearchResponse
import com.furqoncreative.core.data.source.remote.response.recipescategory.RecipesCategoryResponse
import com.furqoncreative.core.data.source.remote.response.recipeslist.RecipesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("recipes")
    suspend fun getAllRecipes(): RecipesListResponse

    @GET("recipe/{key}")
    suspend fun getRecipe(@Path("key") key: String): RecipeDetailResponse

    @GET("categorys/recipes")
    suspend fun getAllRecipesCategory(): RecipesCategoryResponse

    @GET("search")
    suspend fun getAllRecipesBySearch(@Query("q") q: String): RecipesBySearchResponse

    @GET("categorys/recipes/{key}")
    suspend fun getAllRecipesByCategory(@Path("key") key: String): RecipesByCategoryResponse
}
