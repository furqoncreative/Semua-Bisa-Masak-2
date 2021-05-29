package com.furqoncreative.core.data.source.local.room

import androidx.room.*
import com.furqoncreative.core.data.source.local.entity.recipe.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe WHERE `key` = :param")
    fun getRecipe(param:String): Flow<RecipeEntity>

    @Query("SELECT * FROM recipe where isFavorite = 1")
    fun getFavoriteRecipe(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipes: RecipeEntity)

    @Update
    fun updateFavoriteRecipe(recipe: RecipeEntity)
}
