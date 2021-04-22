package com.test.search

import com.test.common.network.APIClient

class SearchUseCaseFactory {

    fun createSearchAlbumsUseCase(apiClient: APIClient) : SearchAlbumsUseCase =
        SearchAlbumsUseCaseImpl(apiClient = apiClient)
}