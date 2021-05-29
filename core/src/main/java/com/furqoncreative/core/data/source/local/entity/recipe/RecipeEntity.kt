package com.furqoncreative.core.data.source.local.entity.recipe

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class RecipeEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    var key: String,

    @ColumnInfo(name = "servings")
    var servings: String,

    @ColumnInfo(name = "times")
    var times: String,

    @ColumnInfo(name = "ingredient")
    var ingredient: String,

    @ColumnInfo(name = "thumb")
    var thumb: String? = null,

    @ColumnInfo(name = "step")
    var step: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "dificulty")
    var dificulty: String,

    @field:SerializedName("desc")
    var desc: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)