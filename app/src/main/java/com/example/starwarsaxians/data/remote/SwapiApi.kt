package com.example.starwarsaxians.data.remote

import com.example.starwarsaxians.data.remote.dtos.CharacterDto
import com.example.starwarsaxians.data.remote.dtos.FilmDto
import com.example.starwarsaxians.data.remote.dtos.CharacterResponseDto
import com.example.starwarsaxians.data.remote.dtos.FilmsResponseDto
import com.example.starwarsaxians.data.remote.dtos.PlanetDto
import com.example.starwarsaxians.data.remote.dtos.PlanetsResponseDto
import com.example.starwarsaxians.data.remote.dtos.SpeciesDto
import com.example.starwarsaxians.data.remote.dtos.SpeciesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SwapiApi {

    @GET("people/")
    suspend fun getPeople(
        @Query("page") page: Int,
        @Query("search") search: String? = null
    ): CharacterResponseDto

    @GET("films/")
    suspend fun getFilms(
        @Query("page") page: Int,
        @Query("search") search: String? = null
    ): FilmsResponseDto

    @GET("planets/")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("search") search: String? = null
    ): PlanetsResponseDto

    @GET("species/")
    suspend fun getSpecies(
        @Query("page") page: Int,
        @Query("search") search: String? = null
    ): SpeciesResponseDto

    @GET("people/{id}/")
    suspend fun getCharacterById(@Path("id") id: String): CharacterDto

    @GET("people/")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): CharacterResponseDto

    @GET
    suspend fun getPlanetByUrl(@Url url: String): PlanetDto

    @GET
    suspend fun getFilmByUrl(@Url url: String): FilmDto

    @GET
    suspend fun getSpeciesByUrl(@Url url: String): SpeciesDto
}