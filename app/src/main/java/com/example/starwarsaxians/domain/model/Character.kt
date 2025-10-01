package com.example.starwarsaxians.domain.model

data class Character(
    val id: String,
    val name: String,
    val gender: String?,
    val birthYear: String?,
    val homeworldId: String?,
    val filmIds: List<String>,
    val speciesIds: List<String>
)