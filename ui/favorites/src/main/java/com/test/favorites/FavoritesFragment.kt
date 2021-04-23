package com.test.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.test.common.mapToUnit
import com.test.favorites.databinding.FavoritesLayoutBinding
import com.test.presentation.FavoritesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import reactivecircus.flowbinding.lifecycle.events

@FlowPreview
@ExperimentalCoroutinesApi
class FavoritesFragment: Fragment(R.layout.favorites_layout) {

    private var binding: FavoritesLayoutBinding? = null
    private val viewModel by viewModel<FavoritesViewModel>()
    private val adapter = FavoritesAdapter()

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
    }

    private fun setupUI() {
        activity?.title = getString(R.string.favorites_title)
        binding?.recyclerView?.let {
            it.adapter = this.adapter
            it.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        binding?.searchButton?.setOnClickListener {
            navigateToSearch()
        }
    }

    private fun bindViewModel() {
        // Input
        val output = viewModel.bind(
            FavoritesViewModel.Input(
                refresh = lifecycle.events().filter { it == Lifecycle.Event.ON_RESUME }.mapToUnit(),
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

    // Navigation

    private fun navigateToAlbumDetails(id: String) {
        val destination = FavoritesFragmentDirections.fromFavoritesToAlbumDetails(id)
        navigate(destination)
    }

    private fun navigateToSearch() {
        val destination = FavoritesFragmentDirections.fromFavoritesToSearch()
        navigate(destination)
    }

    private fun navigate(destination: NavDirections) {
        val navController = findNavController(requireView())
        navController.navigateUp()
        navController.navigate(destination)
    }
}