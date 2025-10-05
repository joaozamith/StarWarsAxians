package com.example.starwarsaxians.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("app_preferences")

class ThemePreferences(private val context: Context) {

    companion object {
        private val IS_DARTH_VADER_MODE = booleanPreferencesKey("is_darth_vader_mode")
    }

    val isDarthVaderMode: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[IS_DARTH_VADER_MODE] ?: false }

    suspend fun setDarthVaderMode(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_DARTH_VADER_MODE] = enabled
        }
    }
}