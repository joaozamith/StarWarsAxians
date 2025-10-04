package com.example.starwarsaxians.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.Film
import com.example.starwarsaxians.domain.model.toDomain

class FilmsPagingSource(
    private val api: SwapiApi,
    private val searchQuery: String?
) : PagingSource<Int,Film>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        val page = params.key ?: 1
        return try {
            val response = api.getFilms(page, searchQuery)
            val films = response.results.map { it.toDomain() }
            LoadResult.Page(
                data = films,
                prevKey = if (response.previous == null) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? = null
}