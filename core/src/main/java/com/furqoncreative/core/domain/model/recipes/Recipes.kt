package com.furqoncreative.core.domain.model.recipes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(
    val times: String? = null,
    val thumb: String? = null,
    val portion: String? = null,
    val title: String? = null,
    val key: String? = null,
    val dificulty: String? = null,
) : Parcelable