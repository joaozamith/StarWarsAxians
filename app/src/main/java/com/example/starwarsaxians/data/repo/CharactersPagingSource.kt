package com.example.starwarsaxians.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.domain.model.toDomain

class CharactersPagingSource(
    private val api: SwapiApi,
    private val searchQuery: String?,
    private val ascending: Boolean? = null
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val response = api.getPeople(page, searchQuery)
            var characters = response.results.map { it.toDomain() }
            if (ascending != null) {
                characters = if (ascending) {
                    characters.sortedBy { it.name.lowercase() }
                } else {
                    characters.sortedByDescending { it.name.lowercase() }
                }
            }

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