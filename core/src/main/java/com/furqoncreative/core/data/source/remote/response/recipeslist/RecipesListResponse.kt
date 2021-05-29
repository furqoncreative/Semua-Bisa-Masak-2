package com.furqoncreative.core.data.source.remote.response.recipeslist

import com.google.gson.annotations.SerializedName

data class RecipesListResponse(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("results")
	val results: List<RecipesListItem>,

	@field:SerializedName("status")
	val status: Boolean
)