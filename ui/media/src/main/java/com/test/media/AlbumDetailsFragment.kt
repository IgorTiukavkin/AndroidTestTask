package com.test.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.test.media.databinding.FragmentAlbumDetailsBinding

class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details) {

    private val args by navArgs<AlbumDetailsFragmentArgs>()
    private var binding: FragmentAlbumDetailsBinding? = null

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
        val albumId = args.albumId
        binding?.textView?.text = "Album ID: $albumId"
    }
}