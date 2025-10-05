package com.example.starwarsaxians.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey val id: String,
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
    val url: String
)