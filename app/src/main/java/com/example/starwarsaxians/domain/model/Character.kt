package com.example.starwarsaxians.domain.model

import com.example.starwarsaxians.data.local.entities.CharacterEntity
import com.example.starwarsaxians.data.helpers.extractId
import com.example.starwarsaxians.data.remote.dtos.CharacterDto

data class Character(
    val id: String,
    val name: String,
    val mass: String?,
    val height: String?,
    val gender: String?,
    val birthYear: String?,
    val homeworldId: String?,
    val filmIds: List<String>,
    val speciesIds: List<String>,
    val vehicleIds: List<String>,
    val starshipIds: List<String>,
    val isFavorite: Boolean
)

fun CharacterDto.toDomain() = Character(
    id = url.extractId(),
    name = name,
    mass = mass,
    height = height,
    gender = gender,
    birthYear = birthYear,
    homeworldId = homeworld?.extractId(),
    filmIds = films.map { it.extractId() },
    speciesIds = species.map { it.extractId() },
    vehicleIds = vehicles.map { it.extractId() },
    starshipIds = starships.map { it.extractId() },
    isFavorite = false
)

fun CharacterEntity.toDomain() = Character(
    id = id,
    name = name,
    mass = mass,
    height = height,
    gender = gender,
    birthYear = birthYear,
    homeworldId = homeworldId,
    filmIds = filmIds,
    speciesIds = speciesIds,
    vehicleIds = vehicleIds,
    starshipIds = starshipIds,
    isFavorite = isFavorite
)

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    mass = mass,
    height = height,
    gender = gender,
    birthYear = birthYear,
    homeworldId = homeworldId,
    filmIds = filmIds,
    speciesIds = speciesIds,
    vehicleIds = vehicleIds,
    starshipIds = starshipIds,
    isFavorite = isFavorite
)