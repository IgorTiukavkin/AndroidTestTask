package com.test.search

import com.test.common.models.AlbumModel
import com.test.common.network.APIClient
import java.lang.Exception

interface SearchAlbumsUseCase {
    suspend fun searchAlbums(name: String) : List<AlbumModel>
}

class SearchAlbumsUseCaseImpl(
    private val apiClient: APIClient
) : SearchAlbumsUseCase {

    override suspend fun searchAlbums(name: String): List<AlbumModel> {
        return try {
            apiClient.searchAlbums(name)
        } catch (e: Exception) {
            emptyList()
        }
    }
}