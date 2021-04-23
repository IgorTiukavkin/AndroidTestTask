package com.test.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.test.media.databinding.FragmentAlbumDetailsBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.view.clicks

@FlowPreview
@ExperimentalCoroutinesApi
class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details) {

    private val args by navArgs<AlbumDetailsFragmentArgs>()
    private var binding: FragmentAlbumDetailsBinding? = null
    private val viewModel by viewModel<AlbumDetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
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
        activity?.title = getString(R.string.album_details_title)
        binding?.favoritesButton?.isVisible = false
        binding?.progressBar?.isVisible = true
    }

    private fun bindViewModel() {
        binding?.let { binding ->
            val albumId = args.albumId
            val output = viewModel.bind(AlbumDetailsViewModel.Input(
                id = flowOf(albumId),
                toggleFavorites = binding.favoritesButton.clicks()
            ))
            output.album.onEach {
                binding.progressBar.isVisible = false
                binding.albumImage.load(it.image)
                binding.albumName.text = it.name
                binding.artistName.text = it.artist
                binding.favoritesButton.isVisible = true
            }.launchIn(lifecycleScope)
            output.isFavorite.onEach { isFavorite ->
                if (isFavorite) {
                    binding.favoritesButton.text = getString(R.string.favorite_remove)
                } else {
                    binding.favoritesButton.text = getString(R.string.favorite_add)
                }
            }.launchIn(lifecycleScope)
        }
    }
}