package com.example.starwarsaxians.data.remote.dtos

import com.squareup.moshi.Json

data class SpeciesDto(
    val name: String,
    val classification: String?,
    val designation: String?,
    @Json(name = "average_height") val averageHeight: String?,
    @Json(name = "skin_colors") val skinColors: String?,
    @Json(name = "hair_colors") val hairColors: String?,
    @Json(name = "eye_colors") val eyeColors: String?,
    @Json(name = "average_lifespan") val averageLifespan: String?,
    val homeworld: String?,
    val language: String?,
    val people: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)

data class SpeciesResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SpeciesDto>
)