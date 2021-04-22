package com.test.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.common.models.AlbumModel
import com.test.common.withLatestFrom
import com.test.favorites.GetFavoriteByIdUseCase
import com.test.favorites.ManageFavoritesUseCase
import com.test.search.SearchAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class AlbumDetailsViewModel(
    private val searchAlbumsUseCase: SearchAlbumsUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
    private val manageFavoritesUseCase: ManageFavoritesUseCase
) : ViewModel() {

    data class Input(
        val id: Flow<String>,
        val toggleFavorites: Flow<Unit>
    )

    data class Output(
        val album: Flow<AlbumDetailsItem>,
        val isFavorite: Flow<Boolean>
    )

    private lateinit var id: String
    private lateinit var album: AlbumDetailsItem
    private val update = BroadcastChannel<Boolean>(1)

    fun bind(input: Input) : Output {

        input.toggleFavorites.onEach {
            val isFavorite = manageFavoritesUseCase.isFavorite(album.id)
            if (isFavorite) manageFavoritesUseCase.removeFavorite(album.id)
            else manageFavoritesUseCase.addFavorite(album.id, album.image, album.name)
            update.offer(!isFavorite)
        }.launchIn(viewModelScope)

        return Output(
            album = input.id.map {
                getFavoriteByIdUseCase.getFavorite(it)
            }.map {
                searchAlbumsUseCase.searchAlbums(it.name).first()
            }.map {
                AlbumDetailsItem(
                    name = it.name,
                    artist = it.artist,
                    image = it.images.lastOrNull()?.url,
                    id = it.id,
                    mbid = it.mbid
                ).also { this.album = it }
            },
            isFavorite = merge(
                input.id.map { manageFavoritesUseCase.isFavorite(it) },
                update.asFlow()
            )
        )
    }
}