package com.test.favorites

import com.test.common.database.DatabaseAPI

class FavoritesUseCaseFactory(
    private val databaseAPI: DatabaseAPI
) {

    fun createGetFavoritesUseCase() : GetFavoritesUseCase {
        return GetFavoritesUseCaseImpl(databaseAPI = databaseAPI)
    }

    fun createManageFavoritesUseCase() : ManageFavoritesUseCase {
        return ManageFavoritesUseCaseImpl(databaseAPI = databaseAPI)
    }

    fun createGetFavoriteByIdUseCase() : GetFavoriteByIdUseCase {
        return GetFavoriteByIdUseCaseImpl(databaseAPI = databaseAPI)
    }
}