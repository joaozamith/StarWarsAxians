package com.example.starwarsaxians.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val climate: String?,
    val population: String?,
    val filmIds: List<String>
)