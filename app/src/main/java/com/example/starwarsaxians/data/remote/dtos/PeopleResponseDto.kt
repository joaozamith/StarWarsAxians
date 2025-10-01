package com.example.starwarsaxians.data.remote.dtos

import com.squareup.moshi.Json

data class PersonDto(
    val name: String,
    val height: String?,
    val mass: String?,
    val gender: String?,
    @Json(name = "birth_year") val birthYear: String?,
    val homeworld: String?,
    val films: List<String>,
    val species: List<String>,
    val url: String,
    @Json(name = "hair_color") val hairColor: String? = null,
    @Json(name = "skin_color") val skinColor: String? = null,
    @Json(name = "eye_color") val eyeColor: String? = null,
    val vehicles: List<String>? = null,
    val starships: List<String>? = null
)

data class PeopleResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PersonDto>
)