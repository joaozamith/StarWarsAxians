package com.example.starwarsaxians.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsaxians.data.local.dao.CharacterDao
import com.example.starwarsaxians.data.local.dao.FilmDao
import com.example.starwarsaxians.data.local.dao.PlanetDao
import com.example.starwarsaxians.data.local.dao.SpeciesDao
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
            pagingSourceFactory = { CharactersPagingSource(api, search) }
        ).flow
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
}