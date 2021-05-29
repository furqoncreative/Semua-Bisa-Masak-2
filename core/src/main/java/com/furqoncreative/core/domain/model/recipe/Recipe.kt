package com.furqoncreative.core.domain.model.recipe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Recipe(
    var key: String? = null,
    var servings: String? = null,
    var times: String? = null,
    var ingredient: List<String>? = null,
    var thumb: String? = null,
    var step: List<String>? = null,
    var title: String? = null,
    var dificulty: String? = null,
    var desc: String? = null,
    var isFavorite: Boolean? = null
) : Parcelable