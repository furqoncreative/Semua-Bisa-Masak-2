package com.furqoncreative.core.data.source.remote.response.recipescategory

import com.google.gson.annotations.SerializedName

data class RecipesCategoryItem(

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)