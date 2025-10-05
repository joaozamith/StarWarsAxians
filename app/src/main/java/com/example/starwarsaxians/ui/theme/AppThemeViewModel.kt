package com.example.starwarsaxians.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsaxians.data.preferences.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppThemeViewModel @Inject constructor(
    private val themePreferences: ThemePreferences
) : ViewModel() {

    private val _isDarthVaderMode = MutableStateFlow(false)
    val isDarthVaderMode: StateFlow<Boolean> = _isDarthVaderMode.asStateFlow()

    init {
        viewModelScope.launch {
            themePreferences.isDarthVaderMode.collectLatest { enabled ->
                _isDarthVaderMode.value = enabled
            }
        }
    }

    fun toggleMode() {
        val newValue = !_isDarthVaderMode.value
        _isDarthVaderMode.value = newValue
        viewModelScope.launch {
            themePreferences.setDarthVaderMode(newValue)
        }
    }
}