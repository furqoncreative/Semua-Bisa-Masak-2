package com.furqoncreative.semuabisamasak.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.ui.CategoryAdapter
import com.furqoncreative.core.ui.RecipesAdapter
import com.furqoncreative.semuabisamasak.R
import com.furqoncreative.semuabisamasak.bycategory.RecipesByCategoryActivity
import com.furqoncreative.semuabisamasak.bysearch.RecipesBySearchActivity
import com.furqoncreative.semuabisamasak.databinding.ActivityHomeBinding
import com.furqoncreative.semuabisamasak.detail.RecipeDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setRecipesData()
        setRecipesCategoryData()

        binding.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val intent = Intent(this, RecipesBySearchActivity::class.java)
                intent.putExtra(RecipesBySearchActivity.EXTRA_DATA, binding.etSearch.text.toString())
                startActivity(intent)
                return@OnEditorActionListener true
            }
            false
        })

        binding.ivSearch.setOnClickListener {
            val intent = Intent(this, RecipesBySearchActivity::class.java)
            intent.putExtra(RecipesBySearchActivity.EXTRA_DATA, binding.etSearch.text.toString())
            startActivity(intent)
        }
        binding.fabFavorite.setOnClickListener {
            val uri = Uri.parse("semuabisamasak://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setRecipesData() {
        val recipesAdapter = RecipesAdapter()
        recipesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        homeViewModel.recipes.observe(this, { recipes ->
            if (recipes != null) {
                when (recipes) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        recipesAdapter.setData(recipes.data)
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

        with(binding.rvRecipes) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recipesAdapter
        }
    }

    private fun setRecipesCategoryData() {
        val categoryAdapter = CategoryAdapter()
        categoryAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, RecipesByCategoryActivity::class.java)
            intent.putExtra(RecipesByCategoryActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        homeViewModel.category.observe(this, { category ->
            if (category != null) {
                when (category) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        categoryAdapter.setData(category.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            category.message ?: getString(R.string.something_wrong)
                        Log.e("TAG", "setRecipesData: ${category.message}")

                    }
                }
            }
        })

        with(binding.rvCategory) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = categoryAdapter
        }
    }

}