package com.test.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val name: String,
    val artist: String,
    val mbid: String,
    @SerialName("image")
    val images: List<Image>
) {

    @Serializable
    data class Image(
        @SerialName("#text")
        val url: String,
        val size: String
    )

    val id: String
        get() = name + artist + mbid
}