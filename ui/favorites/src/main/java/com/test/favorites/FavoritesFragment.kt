package com.test.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.favorites.databinding.FavoritesLayoutBinding

class FavoritesFragment: Fragment(R.layout.favorites_layout) {

    private var binding: FavoritesLayoutBinding? = null

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
        binding?.navigateButton?.setOnClickListener(::navigateToAlbumDetails)
    }

    private fun navigateToAlbumDetails(view: View) {
        val destination = FavoritesFragmentDirections.fromFavoritesToAlbumDetails(15)
        findNavController().navigate(destination)
    }
}