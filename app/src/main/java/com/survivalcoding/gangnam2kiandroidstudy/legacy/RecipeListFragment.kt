package com.survivalcoding.gangnam2kiandroidstudy.legacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.databinding.FragmentRecipeListBinding
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved.SavedRecipeEvent
import com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.saved.SavedRecipeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : Fragment(), OnItemClickListener {
    // ViewBinding: 메모리 누수 방지를 위해 _binding과 binding 구조를 사용
    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedRecipeViewModel by viewModel()

    // Adapter: 데이터와 뷰를 연결하는 중간 다리 역할
    private val recipeAdapter by lazy { RecipeAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    override fun onItemClick(id: Int) {
        Toast.makeText(context, "선택된 레시피 ID: $id", Toast.LENGTH_LONG).show()

        navigateToDetail(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Fragment는 View보다 오래 살기 때문에 메모리 누수를 막기 위해 null 처리
        _binding = null
    }

    private fun setupRecyclerView() {
        // XML에서 만든 RecyclerView애 어댑터를 연결
        binding.recipeRecyclerView.adapter = recipeAdapter
    }

    private fun observeViewModel() {
        /**
         * State 수집 (리스트 데이터 관찰)
         * Flow는 LiveData와 달리 스스로 생명주기를 인식하지 못하므로,
         * lifecycleScope를 통해 뷰가 보일 때만 데이터를 받도록 설정해야 한다.
         */
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { state ->
                    recipeAdapter.submitList(state.data)
                }
            }
        }

        /**
         * Event 수집 (화면 이동 등 1회성 이벤트 처리)
         * SharedFlow는 '화면 이동'처럼 딱 한 번만 발생해야 하는 이벤트를 처리하기 적합.
         */
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        is SavedRecipeEvent.NavigateToDetail -> navigateToDetail(event.recipeId)
                    }
                }
            }
        }
    }

    private fun navigateToDetail(id: Int) {
        val detailFragment = RecipeDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("recipe_id", id)
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

}