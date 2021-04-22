package com.test.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.search.databinding.SearchFragmentLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment(R.layout.search_fragment_layout) {

    private var binding: SearchFragmentLayoutBinding? = null
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SearchFragmentLayoutBinding.inflate(inflater, container, false)
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
        activity?.title = getString(R.string.search_title)
    }

    private fun bindViewModel() {

    }
}