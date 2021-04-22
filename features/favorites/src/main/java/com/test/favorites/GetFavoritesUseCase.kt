package com.test.favorites

import com.test.common.models.FavoriteModel

interface GetFavoritesUseCase {
    suspend fun getFavorites() : List<FavoriteModel>
}

class GetFavoritesUseCaseImpl: GetFavoritesUseCase {

    override suspend fun getFavorites(): List<FavoriteModel> {
//        return emptyList()
        return listOf(
            FavoriteModel(
                albumId = 10,
                imageURLString = "https://images-na.ssl-images-amazon.com/images/I/91YyfXFsTtL._SL1425_.jpg",
                title = "Dookie"
            )
        )
    }
}