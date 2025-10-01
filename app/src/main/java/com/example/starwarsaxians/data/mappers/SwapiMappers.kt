package com.example.starwarsaxians.data.mappers

import com.example.starwarsaxians.data.remote.dtos.FilmDto
import com.example.starwarsaxians.data.remote.dtos.PersonDto
import com.example.starwarsaxians.data.remote.dtos.PlanetDto
import com.example.starwarsaxians.data.remote.dtos.SpeciesDto
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.domain.model.Film
import com.example.starwarsaxians.domain.model.Planet
import com.example.starwarsaxians.domain.model.Species

fun String.extractId(): String = trimEnd('/').substringAfterLast('/')

fun PersonDto.toDomain() = Character(
    id = url.extractId(),
    name = name,
    gender = gender,
    birthYear = birthYear,
    homeworldId = homeworld?.extractId(),
    filmIds = films.map { it.extractId() },
    speciesIds = species.map { it.extractId() }
)

fun PlanetDto.toDomain() = Planet(
    id = url.extractId(),
    name = name,
    climate = climate,
    terrain = terrain
)

fun FilmDto.toDomain() = Film(
    id = url.extractId(),
    title = title,
    releaseYear = release_date.take(4).toIntOrNull() ?: -1
)

fun SpeciesDto.toDomain() = Species(
    id = url.extractId(),
    name = name
)