package com.example.starwarsaxians.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsaxians.data.local.dao.FilmDao
import com.example.starwarsaxians.data.local.entities.FilmEntity
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.domain.model.Film
import com.example.starwarsaxians.domain.model.Planet
import com.example.starwarsaxians.domain.model.Species
import com.example.starwarsaxians.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val api: SwapiApi,
    private val filmDao: FilmDao
) : StarWarsRepository {

    override fun getCharactersPaged(search: String?): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CharactersPagingSource(api, search) }
        ).flow
    }

    override suspend fun getFilm(id: String): Film {
        val cached = filmDao.getFilmById(id)
        if (cached != null) {
            return Film(
                cached.id, cached.title, cached.episodeId, cached.releaseYear
            )
        }

        val dto = api.getFilmByUrl("https://swapi.dev/api/films/$id/")
        val film = dto.toDomain()

        filmDao.insertFilms(
            listOf(
                FilmEntity(film.id, film.title, film.episodeId, film.releaseYear)
            )
        )

        return film
    }

    override suspend fun getPlanet(id: String): Planet {
        val dto = api.getPlanetByUrl("https://swapi.dev/api/planets/$id/")
        return dto.toDomain()
    }


    override suspend fun getSpecies(id: String): Species {
        val dto = api.getSpeciesByUrl("https://swapi.dev/api/species/$id/")
        return dto.toDomain()
    }
}