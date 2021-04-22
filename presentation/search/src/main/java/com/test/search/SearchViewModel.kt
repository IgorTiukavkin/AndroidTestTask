package com.test.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.models.AlbumModel
import com.test.common.withLatestFrom
import com.test.favorites.ManageFavoritesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*
import java.time.Duration

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(
    private val searchAlbumsUseCase: SearchAlbumsUseCase,
    private val manageFavoritesUseCase: ManageFavoritesUseCase
) : ViewModel() {

    data class Input(
        val name: Flow<String>,
        val didSelectAtIndex: Flow<Int>
    )

    data class Output(
        val albums: Flow<List<AlbumListItem>>
    )

    private val albumModels = MutableSharedFlow<List<AlbumModel>>()
    private val update = BroadcastChannel<String>(1)
    private var searchRequest = ""

    fun bind(input: Input) : Output {

        input.didSelectAtIndex
            .withLatestFrom(albumModels) { index, models ->
                val album = models[index]
                val isFavorite = manageFavoritesUseCase.isFavorite(album.id)
                if (isFavorite) manageFavoritesUseCase.removeFavorite(album.id)
                else manageFavoritesUseCase.addFavorite(album.id, album.images.lastOrNull()?.url, album.name)
                update.offer(searchRequest)
            }.launchIn(viewModelScope)

        val albums = merge(input.name, update.asFlow())
            .debounce(200L)
            .map {
                this.searchRequest = it
                searchAlbumsUseCase.searchAlbums(it).also { items ->
                    albumModels.emit(items)
                }
            }

        return Output(
            albums = albums.map { models ->
                models.map {
                    AlbumListItem(
                        imageUrl = it.images.lastOrNull()?.url,
                        name = it.name,
                        isFavorite = manageFavoritesUseCase.isFavorite(it.id)
                    )
                }
            }
        )
    }

}