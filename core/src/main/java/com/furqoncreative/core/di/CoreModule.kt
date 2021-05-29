package com.furqoncreative.core.di

import androidx.room.Room
import com.furqoncreative.core.data.repository.*
import com.furqoncreative.core.data.source.local.LocalDataSource
import com.furqoncreative.core.data.source.local.room.RecipesDatabase
import com.furqoncreative.core.data.source.remote.RemoteDataSource
import com.furqoncreative.core.data.source.remote.network.ApiService
import com.furqoncreative.core.domain.repository.recipe.IRecipeRecpository
import com.furqoncreative.core.domain.repository.recipes.IRecipesRepository
import com.furqoncreative.core.domain.repository.recipesbycategory.IRecipesByCategoryRepository
import com.furqoncreative.core.domain.repository.recipesbysearch.IRecipesBySearchRepository
import com.furqoncreative.core.domain.repository.recipescategory.IRecipesCategoryRepository
import com.furqoncreative.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<RecipesDatabase>().recipeDao() }
    factory { get<RecipesDatabase>().recipesByCategoryDao() }
    factory { get<RecipesDatabase>().recipesCategoryDao() }
    factory { get<RecipesDatabase>().recipesDao() }
    factory { get<RecipesDatabase>().recipesBySearchDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            RecipesDatabase::class.java, "Recipes.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://masak-apa.tomorisakura.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get(), get(), get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRecipesRepository> {
        RecipesRepository(
            get(),
            get()
        )
    }
    single<IRecipesCategoryRepository> {
        RecipesCategoryRepository(
            get(),
            get()
        )
    }
    single<IRecipeRecpository> {
        RecipeRepository(
            get(),
            get(),
            get()
        )
    }
    single<IRecipesByCategoryRepository> {
        RecipesByCategoryRepository(
            get(),
            get()
        )
    }

    single<IRecipesBySearchRepository> {
        RecipesBySearchRepository(
            get(),
            get()
        )
    }

}