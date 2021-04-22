package com.test.app.di

import com.test.favorites.FavoritesUseCaseFactory
import com.test.search.SearchUseCaseFactory
import org.koin.dsl.module

val useCasesModule = module {

    single { FavoritesUseCaseFactory(databaseAPI = get()) }
    factory { (get() as FavoritesUseCaseFactory).createGetFavoritesUseCase() }
    factory { (get() as FavoritesUseCaseFactory).createManageFavoritesUseCase() }

    single { SearchUseCaseFactory() }
    factory { (get() as SearchUseCaseFactory).createSearchAlbumsUseCase(apiClient = get()) }
}