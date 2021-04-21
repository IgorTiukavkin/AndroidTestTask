package com.test.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import com.test.favorites.databinding.FavoritesLayoutBinding
import com.test.presentation.FavoritesViewModel
import kotlinx.coroutines.flow.flowOf

class FavoritesFragment: Fragment(R.layout.favorites_layout) {

    private var binding: FavoritesLayoutBinding? = null
    private val viewModel by viewModel<FavoritesViewModel>()

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
        bindViewModel()
        binding?.navigateButton?.setOnClickListener(::navigateToAlbumDetails)
    }

    private fun bindViewModel() {
        viewModel.bind(
            FavoritesViewModel.Input(
                refresh = flowOf(Unit),
                didSelectAtIndex = flowOf(10)
            )
        )
    }

    private fun navigateToAlbumDetails(view: View) {
        val destination = FavoritesFragmentDirections.fromFavoritesToAlbumDetails(15)
        findNavController().navigate(destination)
    }
}