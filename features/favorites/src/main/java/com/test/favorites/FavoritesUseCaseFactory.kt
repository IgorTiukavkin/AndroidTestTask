package com.test.favorites

class FavoritesUseCaseFactory {

    fun createGetFavoritesUseCase() : GetFavoritesUseCase {
        return GetFavoritesUseCaseImpl()
    }
}