package com.furqoncreative.semuabisamasak.di

import com.furqoncreative.core.domain.usecase.recipe.RecipeInteractor
import com.furqoncreative.core.domain.usecase.recipe.RecipeUseCase
import com.furqoncreative.core.domain.usecase.recipes.RecipesInteractor
import com.furqoncreative.core.domain.usecase.recipes.RecipesUseCase
import com.furqoncreative.core.domain.usecase.recipesbycategory.RecipesByCategoryInteractor
import com.furqoncreative.core.domain.usecase.recipesbycategory.RecipesByCategoryUseCase
import com.furqoncreative.core.domain.usecase.recipesbysearch.RecipesBySearchInteractor
import com.furqoncreative.core.domain.usecase.recipesbysearch.RecipesBySearchUseCase
import com.furqoncreative.core.domain.usecase.recipescategory.RecipesCategoryInteractor
import com.furqoncreative.core.domain.usecase.recipescategory.RecipesCategoryUseCase
import com.furqoncreative.semuabisamasak.bycategory.RecipesByCategoryViewModel
import com.furqoncreative.semuabisamasak.bysearch.RecipesBySearchViewModel
import com.furqoncreative.semuabisamasak.detail.RecipeDetailViewModel
import com.furqoncreative.semuabisamasak.home.HomeViewModel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RecipesUseCase> { RecipesInteractor(get()) }
    factory<RecipesCategoryUseCase> { RecipesCategoryInteractor(get()) }
    factory<RecipeUseCase> { RecipeInteractor(get()) }
    factory<RecipesByCategoryUseCase> { RecipesByCategoryInteractor(get()) }
    factory<RecipesBySearchUseCase> { RecipesBySearchInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { RecipeDetailViewModel(get()) }
    viewModel { RecipesByCategoryViewModel(get()) }
    viewModel { RecipesBySearchViewModel(get()) }
}