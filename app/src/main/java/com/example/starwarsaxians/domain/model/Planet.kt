package com.example.starwarsaxians.domain.model

import com.example.starwarsaxians.data.helpers.extractId
import com.example.starwarsaxians.data.local.entities.PlanetEntity
import com.example.starwarsaxians.data.remote.dtos.PlanetDto

data class Planet(
    val id: String,
    val name: String,
    val climate: String?,
    val population: String?,
    val filmIds: List<String>
)

fun PlanetDto.toDomain() = Planet(
    id = url.extractId(),
    name = name,
    climate = climate,
    population = population,
    filmIds = films.map { it.extractId() }
)

fun PlanetEntity.toDomain() = Planet(
    id = id,
    name = name,
    climate = climate,
    population = population,
    filmIds = filmIds
)

fun Planet.toEntity() = PlanetEntity(
    id = id,
    name = name,
    climate = climate,
    population = population,
    filmIds = filmIds
)