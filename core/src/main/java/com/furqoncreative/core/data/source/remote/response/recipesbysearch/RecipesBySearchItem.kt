package com.furqoncreative.core.data.source.remote.response.recipesbysearch

import com.google.gson.annotations.SerializedName

data class RecipesBySearchItem(

	@field:SerializedName("difficulty")
	val difficulty: String? = null,

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("thumb")
	val thumb: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("serving")
	val serving: String? = null
)