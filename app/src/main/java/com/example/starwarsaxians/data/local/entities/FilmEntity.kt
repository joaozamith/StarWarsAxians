package com.example.starwarsaxians.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey val id: String,
    val title: String,
    val episodeId: Int,
    val releaseYear: Int
)