package com.example.starwarsaxians.domain.model

data class Planet(
    val id: String,
    val name: String,
    val climate: String?,
    val terrain: String?
)