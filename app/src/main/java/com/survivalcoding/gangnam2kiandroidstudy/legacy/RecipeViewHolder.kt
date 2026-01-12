package com.survivalcoding.gangnam2kiandroidstudy.legacy

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.survivalcoding.gangnam2kiandroidstudy.databinding.RecipeItemCardBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

/**
 * ViewHolder는 "어떻게 보여줄 것인가"를 담당
 */
class RecipeViewHolder(
    private val binding: RecipeItemCardBinding,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(recipe: Recipe) {
        with(binding) {
            tvTitle.text = recipe.name
            tvChef.text = "By ${recipe.chef}"
            tvTime.text = recipe.time
            tvRating.text = recipe.rating.toString()

            Glide.with(ivRecipe.context)
                .load(recipe.image)
                .into(ivRecipe)

            root.setOnClickListener {
                listener.onItemClick(recipe.id)
            }
        }
    }
}