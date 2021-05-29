package com.furqoncreative.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furqoncreative.core.data.source.local.entity.recipesbycategory.RecipesByCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesByCategoryDao {

    @Query("SELECT * FROM `recipes-by-category` WHERE category = :param")
    fun getAllRecipesByCategory(param: String): Flow<List<RecipesByCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipesByCategory(recipes: List<RecipesByCategoryEntity>)

}
