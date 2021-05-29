package com.furqoncreative.core.domain.usecase.recipescategory

import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipescategory.RecipesCategory
import kotlinx.coroutines.flow.Flow

interface RecipesCategoryUseCase {
    fun getRecipesCategory(): Flow<Resource<List<RecipesCategory>>>
}