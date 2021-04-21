package com.test.app.di

import com.test.favorites.FavoritesUseCaseFactory
import org.koin.dsl.module

val useCasesModule = module {

    single { FavoritesUseCaseFactory() }
    factory { (get() as FavoritesUseCaseFactory).createGetFavoritesUseCase() }

}