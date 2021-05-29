package com.furqoncreative.core.data.source.remote.response.recipedetail

import com.google.gson.annotations.SerializedName

data class RecipeDetail(

    @field:SerializedName("servings")
    val servings: String,

    @field:SerializedName("times")
    val times: String,

    @field:SerializedName("ingredient")
    val ingredient: List<String>,

    @field:SerializedName("thumb")
    val thumb: String,

    @field:SerializedName("step")
    val step: List<String>,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("dificulty")
    val dificulty: String,

    @field:SerializedName("desc")
    val desc: String
)