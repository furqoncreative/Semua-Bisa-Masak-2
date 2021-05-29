package com.furqoncreative.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furqoncreative.core.R
import com.furqoncreative.core.databinding.ItemListCategoryBinding
import com.furqoncreative.core.domain.model.recipescategory.RecipesCategory
import java.util.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    private var listData = ArrayList<RecipesCategory>()
    var onItemClick: ((RecipesCategory) -> Unit)? = null

    fun setData(newListData: List<RecipesCategory>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_category, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCategoryBinding.bind(itemView)
        fun bind(data: RecipesCategory) {
            with(binding) {
                tvCategory.text = data.category
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}