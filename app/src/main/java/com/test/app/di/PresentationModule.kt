package com.test.app.di

import com.test.presentation.FavoritesViewModel
import com.test.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { FavoritesViewModel(useCase = get()) }
    viewModel { SearchViewModel(searchAlbumsUseCase = get()) }

}