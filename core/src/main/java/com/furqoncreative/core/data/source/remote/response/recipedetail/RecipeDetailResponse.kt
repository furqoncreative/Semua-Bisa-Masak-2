package com.furqoncreative.core.data.source.remote.response.recipedetail

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse(

	@field:SerializedName("method")
	val method: String? = null,

	@field:SerializedName("results")
	val recipeDetail: RecipeDetail? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)