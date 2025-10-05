package com.example.starwarsaxians.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val mass: String?,
    val height: String?,
    val gender: String?,
    val birthYear: String?,
    val homeworldId: String?,
    val filmIds: List<String>,
    val speciesIds: List<String>,
    val vehicleIds: List<String>,
    val starshipIds: List<String>
)

