package com.example.starwarsaxians.data.remote.dtos

import com.squareup.moshi.Json

data class PlanetDto(
    val name: String,
    @Json(name = "rotation_period") val rotationPeriod: String?,
    @Json(name = "orbital_period") val orbitalPeriod: String?,
    val diameter: String?,
    val climate: String?,
    val gravity: String?,
    val terrain: String?,
    @Json(name = "surface_water") val surfaceWater: String?,
    val population: String?,
    val residents: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String
)