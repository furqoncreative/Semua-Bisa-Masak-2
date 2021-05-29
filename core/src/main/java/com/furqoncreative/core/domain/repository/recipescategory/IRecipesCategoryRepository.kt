package com.furqoncreative.core.domain.repository.recipescategory

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipescategory.RecipesCategory
import kotlinx.coroutines.flow.Flow

interface IRecipesCategoryRepository {

    fun getAllRecipesCategory(): Flow<Resource<List<RecipesCategory>>>

}