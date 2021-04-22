package com.test.favorites

import com.test.common.database.DatabaseAPI
import com.test.common.models.FavoriteModel

interface GetFavoriteByIdUseCase {
    suspend fun getFavorite(id: String) : FavoriteModel
}

class GetFavoriteByIdUseCaseImpl(
    private val databaseAPI: DatabaseAPI
) : GetFavoriteByIdUseCase {

    override suspend fun getFavorite(id: String): FavoriteModel =
        databaseAPI.getFavoriteById(id)
}