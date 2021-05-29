package com.furqoncreative.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.furqoncreative.core.domain.model.recipe.Recipe
import com.furqoncreative.core.domain.model.recipes.Recipes
import com.furqoncreative.core.ui.RecipesAdapter
import com.furqoncreative.favorite.databinding.ActivityFavoriteBinding
import com.furqoncreative.favorite.di.favoriteModule
import com.furqoncreative.semuabisamasak.detail.RecipeDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)
        supportActionBar?.hide()

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        val favoriteAdapter = RecipesAdapter()
        favoriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra(RecipeDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favorite.observe(this, { data ->
            favoriteAdapter.setData(mapToRecipes(data))
            binding.viewEmpty.tvEmpty.text = getString(R.string.text_no_favorite)
            binding.viewEmpty.root.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvRecipes) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun mapToRecipes(data: List<Recipe>) = data.map {
        Recipes(
            times = it.times,
            thumb = it.thumb,
            portion = it.servings,
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