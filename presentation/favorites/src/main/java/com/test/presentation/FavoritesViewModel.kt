package com.test.presentation

import androidx.lifecycle.ViewModel
import com.test.favorites.GetFavoritesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesViewModel(
    private val useCase: GetFavoritesUseCase
): ViewModel() {

    data class Input(
        val refresh: Flow<Unit>,
        val didSelectAtIndex: Flow<Int>
    )

    data class Output(
        val items: Flow<List<FavoriteItem>>,
        val routeToAlbumId: Flow<String>
    )

    fun bind(input: Input) : Output {

        val items = input
            .refresh
            .map { useCase.getFavorites() }

        val routeToAlbumId = input
            .didSelectAtIndex.map {
                val objects = useCase.getFavorites()
                objects[it].albumId
            }

        return Output(
            items = items.map { models ->
                models.map {
                    FavoriteItem(
                        imageURL= it.imageURL,
                        title = it.name
                    )
                }
            },
            routeToAlbumId = routeToAlbumId
        )
    }
}