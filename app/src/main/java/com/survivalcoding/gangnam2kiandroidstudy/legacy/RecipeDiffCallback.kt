package com.survivalcoding.gangnam2kiandroidstudy.legacy

import androidx.recyclerview.widget.DiffUtil
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

/**
 * 두 객체의 내용이 같은지, 고유 식별자가 같은지 비교 로직을 제공.
 * 이를 통해 데이터가 100개일 때 1개만 바뀌면 그 1개만 다시 그리도록 최적화.
 */
object RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(
        oldItem: Recipe,
        newItem: Recipe,
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Recipe,
        newItem: Recipe,
    ): Boolean = oldItem == newItem
}