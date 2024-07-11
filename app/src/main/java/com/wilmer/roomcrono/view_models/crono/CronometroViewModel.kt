package com.wilmer.roomcrono.view_models.crono

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wilmer.roomcrono.repositories.crono.CronosRepository
import com.wilmer.roomcrono.state.crono.CronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CronometroViewModel @Inject constructor(private val repository: CronosRepository) : ViewModel() {
    var state by mutableStateOf(CronoState())
        private set

    var cronoJob by mutableStateOf<Job?>(null)
        private set

    var tiempo by mutableStateOf(0L)
        private set

    fun getCronoById(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCronoById(id).collect{item ->
                if (item != null){
                    tiempo = item.crono
                    state = state.copy(title = item.title)
                } else {
                    Log.d("Error", "El objeto crono es nullo")
                }
            }
        }
    }

    fun onValue(value: String) {
        state = state.copy(title = value)
    }

    fun iniciar() {
        state = state.copy(
            cronometroActivo = true
        )
    }

    fun pausar() {
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = true,
        )
    }

    fun detener() {
        cronoJob?.cancel()
        tiempo = 0
        state = state.copy(
            cronometroActivo = false,
            showSaveButton = false,
            showTextField = false
        )
    }

    fun showTextField() {
        state = state.copy(
            showTextField = true
        )
    }

    fun cronos() {
        if (state.cronometroActivo) {
            cronoJob?.cancel()
            cronoJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    tiempo += 1000
                }
            }
        } else {
            cronoJob?.cancel()
        }
    }
}