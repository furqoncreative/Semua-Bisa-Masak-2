package com.furqoncreative.semuabisamasak.bysearch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.core.data.Resource
import com.furqoncreative.core.domain.model.recipes.Recipes
import com.furqoncreative.core.domain.model.recipesbysearch.RecipesBySearch
import com.furqoncreative.core.ui.RecipesAdapter
import com.furqoncreative.semuabisamasak.R
import com.furqoncreative.semuabisamasak.bycategory.RecipesByCategoryActivity
import com.furqoncreative.semuabisamasak.databinding.ActivityRecipesBySearchBinding
import com.furqoncreative.semuabisamasak.detail.RecipeDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RecipesBySearchActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val viewModel: RecipesBySearchViewModel by viewModel()
    private lateinit var binding: ActivityRecipesBySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesBySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        val data = intent.getStringExtra(RecipesByCategoryActivity.EXTRA_DATA)
        setRecipesData(data)

        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setRecipesData(binding.etSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        binding.ivSearch.setOnClickListener {
            setRecipesData(binding.etSearch.text.toString())
        }

    }

    private fun setRecipesData(data: String?) {
        val recipesAdapter = RecipesAdapter()
        recipesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.etSearch.setText(data)
        data?.let {
            viewModel.recipes(it).observe(this, { recipes ->
                if (recipes != null) {
                    when (recipes) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val result = recipes.data?.let { it1 -> mapToRecipes(it1) }
                            Log.d("TAG", "setRecipesData 1: $result")
                            Log.d("TAG", "setRecipesData 2: ${recipes.data}")

                            if (!result.isNullOrEmpty()) {
                                recipesAdapter.setData(result)
                            } else {
                                binding.viewEmpty.tvEmpty.text = "$data : ${getString(R.string.text_no_result)}"
                                binding.viewEmpty.root.visibility = View.VISIBLE
                            }
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

        with(binding.rvRecipes) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recipesAdapter
        }
    }

    private fun mapToRecipes(data: List<RecipesBySearch>) = data.map {
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