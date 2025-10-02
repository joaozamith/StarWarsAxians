package com.example.starwarsaxians.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsaxians.data.local.entities.FilmEntity

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Query("SELECT * FROM films WHERE id = :id LIMIT 1")
    suspend fun getFilmById(id: String): FilmEntity?

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmEntity>
}