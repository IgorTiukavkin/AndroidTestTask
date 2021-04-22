package com.test.favorites

import com.test.common.database.DatabaseAPI
import com.test.common.models.FavoriteModel

interface GetFavoritesUseCase {
    suspend fun getFavorites() : List<FavoriteModel>
}

class GetFavoritesUseCaseImpl(
    private val databaseAPI: DatabaseAPI
): GetFavoritesUseCase {

    override suspend fun getFavorites(): List<FavoriteModel> {
        return databaseAPI.getFavorites()
    }
}