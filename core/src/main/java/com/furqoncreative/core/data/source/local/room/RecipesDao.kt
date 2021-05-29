package com.furqoncreative.core.data.source.local.room

import androidx.room.*
import com.furqoncreative.core.data.source.local.entity.recipes.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipesEntity>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipesEntity>)


}
