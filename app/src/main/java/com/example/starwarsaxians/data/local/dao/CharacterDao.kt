package com.example.starwarsaxians.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsaxians.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: String): CharacterEntity?

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getFavoriteCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getCharactersAsc(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM characters ORDER BY name DESC")
    fun getCharactersDesc(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}