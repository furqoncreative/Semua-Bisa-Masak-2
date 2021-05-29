package com.furqoncreative.core.data.source.remote.response.recipescategory

import com.google.gson.annotations.SerializedName

data class RecipesCategoryResponse(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("results")
	val results: List<RecipesCategoryItem>,

	@field:SerializedName("status")
	val status: Boolean
)