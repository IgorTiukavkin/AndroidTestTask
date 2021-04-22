package com.test.common.network

import com.test.common.models.AlbumModel
import com.test.common.response.AlbumsSearchResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface APIClient {
    suspend fun searchAlbums(name: String) : List<AlbumModel>
}

class LastFMApiClient: APIClient {

    companion object {
        private const val API_KEY = "a5eff627e5b88d5c13741ed960dcd990"
        private const val BASE_URL = "https://ws.audioscrobbler.com"
    }

    @Suppress("FunctionName")
    object ENDPOINTS {
        fun ALBUM_SEARCH(name: String) : String
            = BASE_URL + "/2.0/?method=album.search&album=${name}&api_key=${API_KEY}&format=json"
    }

    private val client = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    override suspend fun searchAlbums(name: String): List<AlbumModel> {
        val response: AlbumsSearchResponse = client.get(ENDPOINTS.ALBUM_SEARCH(name))
        return response.results.albummatches.album
    }
}