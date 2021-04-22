package com.test.search

import com.test.common.models.AlbumModel
import com.test.common.network.APIClient

interface SearchAlbumsUseCase {
    suspend fun searchAlbums(name: String) : List<AlbumModel>
}

class SearchAlbumsUseCaseImpl(
    private val apiClient: APIClient
) : SearchAlbumsUseCase {

    override suspend fun searchAlbums(name: String): List<AlbumModel> {
        return apiClient.searchAlbums(name)
    }
}