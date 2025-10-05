package com.example.starwarsaxians.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.starwarsaxians.data.helpers.extractId
import com.example.starwarsaxians.data.local.dao.CharacterDao
import com.example.starwarsaxians.data.local.dao.FilmDao
import com.example.starwarsaxians.data.local.dao.PlanetDao
import com.example.starwarsaxians.data.local.dao.SpeciesDao
import com.example.starwarsaxians.data.mediator.CharactersRemoteMediator
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.domain.model.Film
import com.example.starwarsaxians.domain.model.Planet
import com.example.starwarsaxians.domain.model.Species
import com.example.starwarsaxians.domain.model.toDomain
import com.example.starwarsaxians.domain.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class StarWarsRepositoryImpl @Inject constructor(
    private val api: SwapiApi,
    private val characterDao: CharacterDao,
    private val filmDao: FilmDao,
    private val speciesDao: SpeciesDao,
    private val planetDao: PlanetDao
) : StarWarsRepository {

    override fun getCharactersPaged(search: String?): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CharactersRemoteMediator(api, characterDao),
            pagingSourceFactory = { characterDao.getAllCharacters() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override fun getCharactersPagedSorted(search: String?, ascending: Boolean): Flow<PagingData<Character>> {
        val pagingSourceFactory = {
            if (ascending) characterDao.getCharactersAsc()
            else characterDao.getCharactersDesc()
        }

        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override fun getFilmsPaged(search: String?): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { FilmsPagingSource(api, search) }
        ).flow
    }

    override fun getPlanetsPaged(search: String?): Flow<PagingData<Planet>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { PlanetsPagingSource(api, search) }
        ).flow
    }

    override fun getSpeciesPaged(search: String?): Flow<PagingData<Species>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SpeciesPagingSource(api, search) }
        ).flow
    }

    override suspend fun getCharacterById(id: String): Character {
        val cached = characterDao.getCharacterById(id)
        if (cached != null) {
            return cached.toDomain()
        }

        val dto = api.getCharacterById(id)
        val character = dto.toDomain()

        characterDao.insertCharacters(listOf(character.toEntity()))

        return character
    }

    override suspend fun toggleFavorite(characterId: String) {
        val character = characterDao.getCharacterById(characterId)
        if (character != null) {
            characterDao.updateFavoriteStatus(characterId, !character.isFavorite)
        }
    }

    override fun getFavoriteCharacters(): Flow<List<Character>> {
        return characterDao.getFavoriteCharacters()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getAllPlanets(): List<Planet> {
        val cached = planetDao.getAllPlanets()
        if (cached.isNotEmpty()) return cached.map { it.toDomain() }

        val response = api.getPlanets(1) // SWAPI paginates, we’ll fetch the first few pages
        val allPlanets = response.results.map { it.toDomain() }

        planetDao.insertPlanets(allPlanets.map { it.toEntity() })

        return allPlanets
    }

    override suspend fun getCharactersByPlanet(planetId: String): List<Character> {
        val characters = mutableListOf<Character>()
        var nextPage: String?
        var currentPage = 1
        do {
            val response = api.getCharacters(page = currentPage)
            response.results
                .filter { it.homeworld?.extractId() == planetId }
                .mapTo(characters) { it.toDomain() }

            nextPage = response.next
            currentPage++
        } while (nextPage != null)

        return characters
    }
}