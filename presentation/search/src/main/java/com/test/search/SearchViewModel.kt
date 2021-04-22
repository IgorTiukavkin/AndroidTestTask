package com.test.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchViewModel(
    private val searchAlbumsUseCase: SearchAlbumsUseCase
) : ViewModel() {

    data class Input(
        val name: Flow<String>
    )

    data class Output(
        val albums: Flow<List<AlbumListItem>>
    )

    fun bind(input: Input) : Output {

        val albums = input.name.map { searchAlbumsUseCase.searchAlbums(it) }

        return Output(
            albums = albums.map { models ->
                models.map {
                    AlbumListItem(
                        imageUrl = it.images.firstOrNull()?.url,
                        name = it.name,
                        isFavorite = false
                    )
                }
            }
        )
    }

}