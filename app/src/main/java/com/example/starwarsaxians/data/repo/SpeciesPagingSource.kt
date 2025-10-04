package com.example.starwarsaxians.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.domain.model.Species
import com.example.starwarsaxians.domain.model.toDomain

class SpeciesPagingSource(
    private val api: SwapiApi,
    private val searchQuery: String?
) : PagingSource<Int, Species>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Species> {
        val page = params.key ?: 1
        return try {
            val response = api.getSpecies(page, searchQuery)
            val species = response.results.map { it.toDomain() }
            LoadResult.Page(
                data = species,
                prevKey = if (response.previous == null) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Species>): Int? = null
}
