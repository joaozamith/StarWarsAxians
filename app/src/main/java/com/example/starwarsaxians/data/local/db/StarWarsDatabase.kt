package com.example.starwarsaxians.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsaxians.data.local.dao.CharacterDao
import com.example.starwarsaxians.data.local.dao.FilmDao
import com.example.starwarsaxians.data.local.dao.PlanetDao
import com.example.starwarsaxians.data.local.dao.SpeciesDao
import com.example.starwarsaxians.data.local.entities.CharacterEntity
import com.example.starwarsaxians.data.local.entities.FilmEntity
import com.example.starwarsaxians.data.local.entities.PlanetEntity
import com.example.starwarsaxians.data.local.entities.SpeciesEntity

@Database(
    entities = [CharacterEntity::class, FilmEntity::class, PlanetEntity::class, SpeciesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun filmDao(): FilmDao
    abstract fun planetDao(): PlanetDao
    abstract fun speciesDao(): SpeciesDao
}