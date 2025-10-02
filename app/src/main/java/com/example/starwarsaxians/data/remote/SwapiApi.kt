package com.example.starwarsaxians.data.remote

import com.example.starwarsaxians.data.remote.dtos.FilmDto
import com.example.starwarsaxians.data.remote.dtos.CharacterResponseDto
import com.example.starwarsaxians.data.remote.dtos.PlanetDto
import com.example.starwarsaxians.data.remote.dtos.SpeciesDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SwapiApi {

    @GET("people/")
    suspend fun getPeople(
        @Query("page") page: Int,
        @Query("search") search: String? = null
    ): CharacterResponseDto

    @GET
    suspend fun getPlanetByUrl(@Url url: String): PlanetDto

    @GET
    suspend fun getFilmByUrl(@Url url: String): FilmDto

    @GET
    suspend fun getSpeciesByUrl(@Url url: String): SpeciesDto
}