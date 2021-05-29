package com.furqoncreative.core.data.source.local.entity.recipescategory

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes-category")
data class RecipesCategoryEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "category")
    val category: String? = null,

    @ColumnInfo(name = "url")
    val url: String? = null,
)