package com.furqoncreative.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furqoncreative.core.data.source.local.entity.recipescategory.RecipesCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesCategoryDao {

    @Query("SELECT * FROM `recipes-category`")
    fun getAllRecipesCategory(): Flow<List<RecipesCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipesCategory(recipes: List<RecipesCategoryEntity>)

}
