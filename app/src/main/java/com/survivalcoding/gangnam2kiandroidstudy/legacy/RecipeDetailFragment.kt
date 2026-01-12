package com.survivalcoding.gangnam2kiandroidstudy.legacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * 데이터 수신
         * 이전 Fragment에서 arguments(Bundle)에 담아 보낸 데이터를 키값("recipe_id")을 통해 꺼낸다.
         */
        val recipeId = arguments?.getInt("recipe_id") ?: 0

        binding.tvDetailId.text = "Recipe Id: $recipeId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}