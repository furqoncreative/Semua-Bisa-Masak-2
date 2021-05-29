package com.furqoncreative.semuabisamasak.bycategory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipes.Recipes
import com.furqoncreative.core.domain.model.recipesbycategory.RecipesByCategory
import com.furqoncreative.core.domain.model.recipescategory.RecipesCategory
import com.furqoncreative.core.ui.RecipesAdapter
import com.furqoncreative.semuabisamasak.R
import com.furqoncreative.semuabisamasak.databinding.ActivityRecipesByCategoryBinding
import com.furqoncreative.semuabisamasak.detail.RecipeDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RecipesByCategoryActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val viewModel: RecipesByCategoryViewModel by viewModel()
    private lateinit var binding: ActivityRecipesByCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesByCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        setRecipesData()

    }

    private fun setRecipesData() {
        val recipesAdapter = RecipesAdapter()
        recipesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val data = intent.getParcelableExtra<RecipesCategory>(EXTRA_DATA)
        if (data != null) {
            binding.tvCategory.text = data.category
            data.key?.let {
                viewModel.recipes(it).observe(this, { recipes ->
                    if (recipes != null) {
                        when (recipes) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                recipesAdapter.setData(recipes.data?.let { it1 -> mapToRecipes(it1) })
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.VISIBLE
                                binding.viewError.tvError.text =
                                    recipes.message ?: getString(R.string.something_wrong)
                                Log.e("TAG", "setRecipesData: ${recipes.message}")

                            }
                        }
                    }
                })
            }
        }

        with(binding.rvRecipes) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recipesAdapter
        }
    }

    private fun mapToRecipes(data: List<RecipesByCategory>) = data.map {
        Recipes(
            times = it.times,
            thumb = it.thumb,
            portion = it.portion,
            title = it.title,
            key = it.key,
            dificulty = it.dificulty
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}