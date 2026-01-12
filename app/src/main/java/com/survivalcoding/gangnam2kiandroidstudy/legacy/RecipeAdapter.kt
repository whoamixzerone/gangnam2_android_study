package com.survivalcoding.gangnam2kiandroidstudy.legacy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.survivalcoding.gangnam2kiandroidstudy.databinding.RecipeItemCardBinding
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

/**
 * ListAdapter가 RecyclerView.Adapter보다 호율적이다.
 * 데이터가 바뀔 때 전체를 refresh 않고 DiffUtil을 통해 바뀐 부분만 계산해서 애니메이션과 함께 업데이트하기 때문이다.
 *
 * 전체 리스트의 흐름을 제어하고 뷰홀더를 생성/연결하는 역할을 수행.
 */
class RecipeAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecipeViewHolder {
        val binding = RecipeItemCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}