package com.example.starwarsaxians.domain.model

import com.example.starwarsaxians.data.helpers.extractId
import com.example.starwarsaxians.data.local.entities.SpeciesEntity
import com.example.starwarsaxians.data.remote.dtos.SpeciesDto

data class Species(
    val id: String,
    val name: String,
    val classification: String?,
    val language: String?,
    val homeworldId: String?,
    val filmIds: List<String>
)

fun SpeciesDto.toDomain() = Species(
    id = url.extractId(),
    name = name,
    classification = classification,
    language = language,
    homeworldId = homeworld?.extractId(),
    filmIds = films.map { it.extractId() }
)

fun SpeciesEntity.toDomain() = Species(
    id = id,
    name = name,
    classification = classification,
    language = language,
    homeworldId = homeworldId,
    filmIds = filmIds
)

fun Species.toEntity() = SpeciesEntity(
    id = id,
    name = name,
    classification = classification,
    language = language,
    homeworldId = homeworldId,
    filmIds = filmIds
)