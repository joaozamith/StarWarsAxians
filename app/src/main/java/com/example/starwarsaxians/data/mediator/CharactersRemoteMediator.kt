package com.example.starwarsaxians.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.starwarsaxians.data.local.dao.CharacterDao
import com.example.starwarsaxians.data.local.entities.CharacterEntity
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.toDomain
import com.example.starwarsaxians.domain.model.toEntity

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val api: SwapiApi, private val characterDao: CharacterDao
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val currentPage = (lastItem?.id?.toIntOrNull() ?: 0) / state.config.pageSize + 1
                    currentPage + 1
                }
            }

            val response = api.getPeople(page)

            val entities = response.results.map { dto -> dto.toDomain().toEntity() }

            if (loadType == LoadType.REFRESH) { characterDao.clearAll() }

            characterDao.insertAll(entities)

            MediatorResult.Success(endOfPaginationReached = response.next == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}