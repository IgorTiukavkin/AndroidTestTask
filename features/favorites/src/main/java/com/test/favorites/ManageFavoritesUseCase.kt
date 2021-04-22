package com.test.favorites

import com.test.common.database.DatabaseAPI
import com.test.common.models.AlbumModel

interface ManageFavoritesUseCase {
    suspend fun isFavorite(id: String) : Boolean
    suspend fun addFavorite(id: String, imageURL: String?, name: String)
    suspend fun removeFavorite(id: String)
}

class ManageFavoritesUseCaseImpl(
    private val databaseAPI: DatabaseAPI
) : ManageFavoritesUseCase {

    override suspend fun isFavorite(id: String): Boolean {
        return databaseAPI.isFavorite(id)
    }

    override suspend fun addFavorite(id: String, imageURL: String?, name: String) {
        databaseAPI.addFavorite(id, imageURL, name)
    }

    override suspend fun removeFavorite(id: String) {
        databaseAPI.removeFavorite(id)
    }
}