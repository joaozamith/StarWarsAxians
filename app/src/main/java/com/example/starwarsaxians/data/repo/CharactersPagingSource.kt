package com.example.starwarsaxians.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.domain.model.toDomain

class CharactersPagingSource(
    private val api: SwapiApi,
    private val searchQuery: String?
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val response = api.getPeople(page, searchQuery)
            val characters = response.results.map { it.toDomain() }
            LoadResult.Page(
                data = characters,
                prevKey = if (response.previous == null) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null
}