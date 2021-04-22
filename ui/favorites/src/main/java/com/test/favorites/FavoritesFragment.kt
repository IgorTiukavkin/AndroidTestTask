package com.test.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.test.favorites.databinding.FavoritesLayoutBinding
import com.test.presentation.FavoritesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class FavoritesFragment: Fragment(R.layout.favorites_layout) {

    private var binding: FavoritesLayoutBinding? = null
    private val viewModel by viewModel<FavoritesViewModel>()
    private val adapter = FavoritesAdapter()

    // Actions
    private val refreshAction = MutableStateFlow(Unit)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavoritesLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        bindViewModel()
        lifecycleScope.launchWhenResumed {
            refreshAction.emit(Unit)
        }
    }

    private fun setupUI() {
        activity?.title = getString(R.string.favorites_title)
        binding?.recyclerView?.let {
            it.adapter = this.adapter
            it.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun bindViewModel() {
        // Input
        val output = viewModel.bind(
            FavoritesViewModel.Input(
                refresh = refreshAction,
                didSelectAtIndex = adapter.onItemClick.asFlow()
            )
        )
        // Output
        output.items.onEach { favorites ->
            binding?.emptyView?.isVisible = favorites.count() == 0
            binding?.recyclerView?.isVisible = favorites.count() > 0
            adapter.update(favorites)
        }.launchIn(lifecycleScope)
        output.routeToAlbumId.onEach {
            navigateToAlbumDetails(it)
        }.launchIn(lifecycleScope)
    }

    private fun navigateToAlbumDetails(id: String) {
        val destination = FavoritesFragmentDirections.fromFavoritesToAlbumDetails(id)
        val navController = findNavController(requireView())
        navController.navigateUp()
        navController.navigate(destination)
    }
}