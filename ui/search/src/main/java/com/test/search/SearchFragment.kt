package com.test.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.search.databinding.SearchFragmentLayoutBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.widget.editorActionEvents
import reactivecircus.flowbinding.android.widget.textChangeEvents

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment: Fragment(R.layout.search_fragment_layout) {

    private var binding: SearchFragmentLayoutBinding? = null
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = SearchListAdapter()

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
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun bindViewModel() {
        binding?.let { binding ->
            // Input
            val input = SearchViewModel.Input(
                name = binding.textInput.textChangeEvents().map { it.text.toString() },
                didSelectAtIndex = adapter.onItemClick.asFlow()
            )
            // Output
            val output = viewModel.bind(input)
            output.albums.onEach {
                adapter.update(it)
            }.launchIn(lifecycleScope)
        }
    }
}