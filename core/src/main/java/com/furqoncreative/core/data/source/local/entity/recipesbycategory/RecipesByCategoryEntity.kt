package com.furqoncreative.core.data.source.local.entity.recipesbycategory

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes-by-category")
data class RecipesByCategoryEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "times")
    val times: String? = null,

    @ColumnInfo(name = "thumb")
    val thumb: String? = null,

    @ColumnInfo(name = "portion")
    val portion: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "dificulty")
    val dificulty: String? = null,

    @ColumnInfo(name = "category")
    val category: String? = null
)