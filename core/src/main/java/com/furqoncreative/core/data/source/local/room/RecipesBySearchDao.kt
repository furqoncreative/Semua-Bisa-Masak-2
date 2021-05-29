package com.furqoncreative.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furqoncreative.core.data.source.local.entity.recipesbysearch.RecipesBySearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesBySearchDao {

    @Query("SELECT * FROM `recipes-by-search`  WHERE  title LIKE '%' || :param || '%'")
    fun getAllRecipesBySearch(param: String): Flow<List<RecipesBySearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipesBySearch(recipes: List<RecipesBySearchEntity>)

}
