package com.furqoncreative.core.domain.model.recipesbycategory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesByCategory(
    val times: String? = null,
    val thumb: String? = null,
    val portion: String? = null,
    val title: String? = null,
    val key: String? = null,
    val dificulty: String? = null,
    var isFavorite: Boolean = false
) : Parcelable