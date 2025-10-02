package com.example.starwarsaxians.di

import android.content.Context
import androidx.room.Room
import com.example.starwarsaxians.data.local.dao.CharacterDao
import com.example.starwarsaxians.data.local.dao.FilmDao
import com.example.starwarsaxians.data.local.dao.PlanetDao
import com.example.starwarsaxians.data.local.dao.SpeciesDao
import com.example.starwarsaxians.data.local.db.AppDatabase
import com.example.starwarsaxians.data.remote.SwapiApi
import com.example.starwarsaxians.data.repo.StarWarsRepository
import com.example.starwarsaxians.data.repo.StarWarsRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideSwapiApi(retrofit: Retrofit): SwapiApi =
        retrofit.create(SwapiApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: SwapiApi, filmDao: FilmDao): StarWarsRepository =
        StarWarsRepositoryImpl(api, filmDao)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "starwars_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCharacterDao(db: AppDatabase): CharacterDao = db.characterDao()
    @Provides
    fun provideFilmDao(db: AppDatabase): FilmDao = db.filmDao()
    @Provides
    fun providePlanetDao(db: AppDatabase): PlanetDao = db.planetDao()
    @Provides
    fun provideSpeciesDao(db: AppDatabase): SpeciesDao = db.speciesDao()
}