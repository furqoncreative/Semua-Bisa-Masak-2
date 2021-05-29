package com.furqoncreative.core.data.source.remote.response.recipesbycategory

import com.google.gson.annotations.SerializedName

data class RecipesByCategoryResponse(

    @field:SerializedName("method")
	val method: String,

    @field:SerializedName("results")
	val results: List<RecipesByCategoryItem>,

    @field:SerializedName("status")
	val status: Boolean
)