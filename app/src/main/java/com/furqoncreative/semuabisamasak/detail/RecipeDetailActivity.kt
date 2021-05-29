package com.furqoncreative.semuabisamasak.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipes.Recipes
import com.furqoncreative.semuabisamasak.R
import com.furqoncreative.semuabisamasak.databinding.ActivityRecipeDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RecipeDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val viewModel: RecipeDetailViewModel by viewModel()
    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.cvBack.setOnClickListener {
            onBackPressed()
        }
        setRecipeData()
    }

    private fun setRecipeData() {
        val data = intent.getParcelableExtra<Recipes>(EXTRA_DATA)
        if (data != null) {
            data.key?.let {
                viewModel.recipe(it).observe(this) { resource ->
                    if (resource != null) {

                        when (resource) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                val recipe = resource.data
                                if (recipe != null) {
                                    with(binding) {
                                        Glide.with(applicationContext)
                                            .load(recipe.thumb)
                                            .placeholder(R.drawable.img_thumb_placeholder)
                                            .into(ivRecipeThumbnail)
                                        tvRecipeDesc.text = recipe.desc
                                        tvRecipeDificulty.text = recipe.dificulty
                                        tvRecipeName.text = recipe.title
                                        tvRecipeTime.text = recipe.times
                                        tvRecipePortion.text = recipe.servings
                                        tvRecipeIngredients.text = recipe.ingredient?.joinToString("\n")
                                        tvRecipeSteps.text = recipe.step?.joinToString()
                                        tvRecipePortion.text = recipe.servings
                                    }
                                    var statusFavorite = recipe.isFavorite
                                    statusFavorite?.let { it1 -> setStatusFavorite(it1) }
                                        binding.cvFavorite.setOnClickListener {
                                            statusFavorite = !statusFavorite!!
                                            viewModel.setFavoriteRecipes(recipe, statusFavorite!!)
                                            setStatusFavorite(statusFavorite!!)
                                        }

                                }
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.VISIBLE
                                binding.viewError.tvError.text =
                                    resource.message ?: getString(R.string.something_wrong)
                                Log.e("TAG", "setRecipesData: ${resource.message}")

                            }
                        }
                    }
                }
            }
        }


    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_heart_fill
                )
            )
        } else {
            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_heart
                )
            )
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}