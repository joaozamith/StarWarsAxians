package com.example.starwarsaxians.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsaxians.data.local.entities.PlanetEntity

@Dao
interface PlanetDao {
    @Query("SELECT * FROM planets")
    suspend fun getAllPlanets(): List<PlanetEntity>

    @Query("SELECT * FROM planets WHERE id = :id")
    suspend fun getPlanetById(id: String): PlanetEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: PlanetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(planets: List<PlanetEntity>)
}