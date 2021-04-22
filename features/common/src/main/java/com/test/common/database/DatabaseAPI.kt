package com.test.common.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.test.common.models.AlbumModel
import com.test.common.models.FavoriteModel
import com.test.features.common.Database
import com.test.features.common.FavoritesTable

interface DatabaseAPI {
    suspend fun getFavorites() : List<FavoriteModel>
    suspend fun getFavoriteById(id: String) : FavoriteModel
    suspend fun addFavorite(id: String, imageURL: String?, name: String)
    suspend fun removeFavorite(id: String)
    suspend fun isFavorite(id: String) : Boolean
}

class DatabaseAPIImpl(
    context: Context
) : DatabaseAPI {

    private val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, context, "test.db")
    private val database = Database(driver)

    override suspend fun getFavorites(): List<FavoriteModel> =
        database.favoritesTableQueries.selectAll().executeAsList().map {
            FavoriteModel(
                albumId = it.id,
                imageURL = it.imageURL,
                name = it.name
            )
        }

    override suspend fun getFavoriteById(id: String): FavoriteModel  =
        database.favoritesTableQueries.getFavoriteById(id).executeAsList().map {
            FavoriteModel(
                albumId = it.id,
                imageURL = it.imageURL,
                name = it.name
            )
        }.first()

    override suspend fun addFavorite(id: String, imageURL: String?, name: String) {
        database.favoritesTableQueries.transaction {
            database.favoritesTableQueries.addFavorite(
                FavoritesTable(
                    id = id,
                    imageURL = imageURL,
                    name = name
                )
            )
        }
    }

    override suspend fun removeFavorite(id: String) {
        database.favoritesTableQueries.transaction {
            database.favoritesTableQueries.removeFavorite(id)
        }
    }

    override suspend fun isFavorite(id: String): Boolean =
        database.favoritesTableQueries.getFavoriteById(id).executeAsOneOrNull() != null
}