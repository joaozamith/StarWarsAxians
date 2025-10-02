package com.example.starwarsaxians.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsaxians.data.local.entities.SpeciesEntity

@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species WHERE id = :id")
    suspend fun getSpeciesById(id: String): SpeciesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: SpeciesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesList(species: List<SpeciesEntity>)
}