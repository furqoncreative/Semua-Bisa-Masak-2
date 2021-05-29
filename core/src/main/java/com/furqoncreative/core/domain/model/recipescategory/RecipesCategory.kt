package com.furqoncreative.core.domain.model.recipescategory

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesCategory(
    val category: String? = null,
    val url: String? = null,
    val key: String? = null
) : Parcelable