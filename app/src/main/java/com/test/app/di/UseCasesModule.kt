package com.test.app.di

import com.test.favorites.FavoritesUseCaseFactory
import com.test.search.SearchUseCaseFactory
import org.koin.dsl.module

val useCasesModule = module {

    single { FavoritesUseCaseFactory() }
    factory { (get() as FavoritesUseCaseFactory).createGetFavoritesUseCase() }

    single { SearchUseCaseFactory() }
    factory { (get() as SearchUseCaseFactory).createSearchAlbumsUseCase(apiClient = get()) }
}