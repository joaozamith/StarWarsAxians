package com.example.starwarsaxians.data.repo

import androidx.paging.PagingData
import com.example.starwarsaxians.domain.model.*
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {
    fun getCharactersPaged(search: String?): Flow<PagingData<Character>>
    fun getFilmsPaged(search: String?): Flow<PagingData<Film>>
    fun getPlanetsPaged(search: String?): Flow<PagingData<Planet>>
    fun getSpeciesPaged(search: String?): Flow<PagingData<Species>>
    suspend fun getPlanet(id: String): Planet
    suspend fun getFilm(id: String): Film
    suspend fun getSpecies(id: String): Species
}