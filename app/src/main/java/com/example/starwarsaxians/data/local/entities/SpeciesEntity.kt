package com.example.starwarsaxians.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey val id: String,
    val name: String,
    val classification: String?,
    val language: String?,
    val homeworldId: String?,
    val filmIds: List<String>
)