package com.furqoncreative.core.domain.usecase.recipes

import com.furqoncreative.core.domain.repository.recipes.IRecipesRepository

class RecipesInteractor(private val repository: IRecipesRepository) : RecipesUseCase {

    override fun getAllRecipes() = repository.getAllRecipes()

}