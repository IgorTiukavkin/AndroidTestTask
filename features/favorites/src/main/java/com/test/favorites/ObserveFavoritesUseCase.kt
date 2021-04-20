package com.test.favorites

import com.test.common.models.FavoriteModel

interface GetFavoritesUseCase {
    suspend fun getFavorites() : List<FavoriteModel>
}

class GetFavoritesUseCaseImpl {
}