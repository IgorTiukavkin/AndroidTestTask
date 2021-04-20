package com.test.presentation

import androidx.lifecycle.ViewModel
import com.test.common.withLatestFrom
import com.test.favorites.GetFavoritesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.URL

class FavoritesViewModel(
    private val useCase: GetFavoritesUseCase
): ViewModel() {

    data class Input(
        val refresh: Flow<Unit>,
        val didSelectAtIndex: Flow<Int>
    )

    data class Output(
        val items: Flow<List<FavoriteItem>>,
        val routeToAlbumId: Flow<Int>
    )

    fun bind(input: Input) : Output {
        val items = input
            .refresh
            .map { useCase.getFavorites() }

        val routeToAlbumId = input
            .didSelectAtIndex
            .withLatestFrom(items) { index, objects ->
                objects[index]
            }.map { it.albumId }

        return Output(
            items = items.map { models ->
                models.map {
                    FavoriteItem(
                        imageURL = URL(it.imageURLString),
                        title = it.title
                    )
                }
            },
            routeToAlbumId = routeToAlbumId
        )
    }
}