package com.furqoncreative.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.furqoncreative.core.R
import com.furqoncreative.core.databinding.ItemListRecipeBinding
import com.furqoncreative.core.domain.model.recipes.Recipes
import java.util.*

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Recipes>()
    var onItemClick: ((Recipes) -> Unit)? = null

    fun setData(newListData: List<Recipes>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_recipe, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListRecipeBinding.bind(itemView)
        fun bind(data: Recipes) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.thumb)
                    .placeholder(R.drawable.img_thumb_placeholder)
                    .into(ivRecipeThumbnail)
                tvRecipeName.text = data.title
                tvRecipePortion.text = data.portion
                tvRecipeTime.text = data.times
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}