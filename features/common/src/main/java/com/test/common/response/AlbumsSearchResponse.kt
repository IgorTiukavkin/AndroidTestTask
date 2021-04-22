package com.test.common.response

import com.test.common.models.AlbumModel
import kotlinx.serialization.Serializable

@Serializable
data class AlbumsSearchResponse(
    val results: Results
) {

    @Serializable
    data class Results(
        val albummatches: Albummatches
    ) {

        @Serializable
        data class Albummatches(
            val album: List<AlbumModel>
        )

    }
}