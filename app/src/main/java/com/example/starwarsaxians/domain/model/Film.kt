package com.example.starwarsaxians.domain.model

import com.example.starwarsaxians.data.local.entities.FilmEntity
import com.example.starwarsaxians.data.helpers.extractId
import com.example.starwarsaxians.data.remote.dtos.FilmDto

data class Film(
    val id: String,
    val title: String,
    val episodeId: Int,
    val releaseYear: Int
)

fun FilmDto.toDomain() = Film(
    id = url.extractId(),
    title = title,
    episodeId = episodeId,
    releaseYear = releaseDate.take(4).toInt()
)

fun FilmEntity.toDomain() = Film(
    id = id,
    title = title,
    episodeId = episodeId,
    releaseYear = releaseYear
)

fun Film.toEntity() = FilmEntity(
    id = id,
    title = title,
    episodeId = episodeId,
    releaseYear = releaseYear
)