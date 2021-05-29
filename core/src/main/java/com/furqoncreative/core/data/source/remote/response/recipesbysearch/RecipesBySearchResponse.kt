package com.furqoncreative.core.data.source.remote.response.recipesbysearch

import com.google.gson.annotations.SerializedName

data class RecipesBySearchResponse(

    @field:SerializedName("method")
	val method: String,

    @field:SerializedName("results")
	val results: List<RecipesBySearchItem>,

    @field:SerializedName("status")
	val status: Boolean
)