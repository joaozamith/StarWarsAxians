package com.example.starwarsaxians.domain.model

import com.example.starwarsaxians.data.helpers.extractId
import com.example.starwarsaxians.data.local.entities.PlanetEntity
import com.example.starwarsaxians.data.remote.dtos.PlanetDto

data class Planet(
    val id: String,
    val name: String,
    val population: String?,
    val filmIds: List<String>,
    val rotationPeriod: String?,
    val orbitalPeriod: String?,
    val diameter: String?,
    val climate: String?,
    val gravity: String?,
    val terrain: String?,
    val residents: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

fun PlanetDto.toDomain() = Planet(
    id = url.extractId(),
    name = name,
    climate = climate,
    population = population,
    filmIds = films.map { it.extractId() },
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    gravity = gravity,
    terrain = terrain,
    residents = residents,
    films = films,
    created = created,
    edited = edited,
    url = url,
    latitude = generateLatitude(url.extractId()),
    longitude = generateLongitude(url.extractId())
)

fun PlanetEntity.toDomain() = Planet(
    id = id,
    name = name,
    climate = climate,
    population = population,
    filmIds = filmIds,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    gravity = gravity,
    terrain = terrain,
    residents = residents,
    films = films,
    created = created,
    edited = edited,
    url = url
)

fun Planet.toEntity() = PlanetEntity(
    id = id,
    name = name,
    climate = climate,
    population = population,
    filmIds = filmIds,
    rotationPeriod = rotationPeriod,
    orbitalPeriod = orbitalPeriod,
    diameter = diameter,
    gravity = gravity,
    terrain = terrain,
    residents = residents,
    films = films,
    created = created,
    edited = edited,
    url = url
)

private fun generateLatitude(id: String): Double = id.hashCode() % 90 + 0.0
private fun generateLongitude(id: String): Double = id.hashCode() % 180 + 0.0