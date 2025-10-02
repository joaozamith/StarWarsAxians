package com.example.starwarsaxians.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsaxians.data.mappers.toDomain
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val api: SwapiApi
) : StarWarsRepository {

    override fun getCharactersPaged(search: String?): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CharactersPagingSource(api, search) }
        ).flow
    }

    override suspend fun getPlanet(id: String): Planet {
        val dto = api.getPlanetByUrl("https://swapi.dev/api/planets/$id/")
        return dto.toDomain()
    }

    override suspend fun getFilm(id: String): Film {
        val dto = api.getFilmByUrl("https://swapi.dev/api/films/$id/")
        return dto.toDomain()
    }

    override suspend fun getSpecies(id: String): Species {
        val dto = api.getSpeciesByUrl("https://swapi.dev/api/species/$id/")
        return dto.toDomain()
    }
}